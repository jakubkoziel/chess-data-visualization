package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.ChartsService;
import com.example.demo.service.EvaluationService;
import com.example.demo.service.PositionDataService;
import com.example.demo.service.MastersPositionDataService;
import com.example.demo.service.TopPlayersService;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//a bit of refactoriong in this class would help a lot
//there is also a bit of logic that shall be grouped into functions
@Controller
public class NavigationController {

    FENPosition fenPosition = new FENPosition();

    //temporary solution to enable making moves in different ways
    boolean skip = false;

    @Autowired
    ChartsService chartService;

    @Autowired
    PositionDataService positionService;

    @Autowired
    MastersPositionDataService mastersPositionService;

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    TopPlayersService topPlayersService;


    @GetMapping("/player-history")
    public String getTestPage(Model model) {
        model.addAttribute("nickname", new Nickname());
        model.addAttribute("activePage", "playerHistory");

        try {
            model.addAttribute("mojeDane", chartService.getDataForRatingHistory("Jacob17"));
        } catch (IOException e) {
            return "player-history";
        }

        return "player-history";
    }


    @PostMapping("/player-history")
    public String makeTestChart(@ModelAttribute Nickname nickname, Model model) {
        model.addAttribute("activePage", "playerHistory");

        try {
            model.addAttribute("mojeDane", chartService.getDataForRatingHistory(nickname.getNickname()));
        } catch (IOException e) {
            model.addAttribute("nickname", new Nickname());
            return "player-history";
        }

        model.addAttribute("nickname", nickname);
        return "player-history-plot";
    }



    //BEST MOVES LICHESS

    @GetMapping("/best-moves")
    public String getBestMoves(Model model) {

        model.addAttribute("positionData", positionService.getDataPosition(fenPosition.getFENPosition()));
        model.addAttribute("activePage", "bestMoves");
        model.addAttribute("move", new Move(""));
        model.addAttribute("fenPosition", fenPosition);

        // creating the starting arrows
        PositionData positionData = positionService.getDataPosition(fenPosition.getFENPosition());
        List<PopularMoveData> popularMoveData = positionData.getMoves();
        List<PopularMoveData> allMoves = new ArrayList<>();
        List<String> movesUci = new ArrayList<>();
        for (int i = 0; i < Math.min(popularMoveData.size(), 5); i++) {
            movesUci.add(popularMoveData.get(i).getUci());
            allMoves.add(popularMoveData.get(i));
        }
        if (allMoves.size() < 5) {
            for (int i = allMoves.size(); i < 5; i++) {
                PopularMoveData tmp = new PopularMoveData();
                tmp.setAverageRating(0);
                tmp.setBlack(0);
                tmp.setDraws(0);
                tmp.setUci("");
                tmp.setWhite(0);
                allMoves.add(tmp);
            }
        }
        List<TableData> tableData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PopularMoveData popularMoveData1 = allMoves.get(i);
            tableData.add(new TableData(popularMoveData1.getUci(), popularMoveData1.getWhite(),
                    popularMoveData1.getDraws(), popularMoveData1.getBlack()));
        }
        model.addAttribute("tableData", tableData);
        model.addAttribute("movesUci", movesUci);
        model.addAttribute("popularMoves", allMoves);
        // ^
        skip = false;
        return "best-moves";
    }


    @PostMapping(value = "/best-moves", params = {})
    public String makeMove(@ModelAttribute Move move, Model model) {

        if (skip) {
            move.setMove("");
            skip = false;
        }

        model.addAttribute("activePage", "bestMoves");
        model.addAttribute("move", move);

        model = boardLogic(move, model);

        List<PopularMoveData> allMoves = (List) model.getAttribute("popularMoves");

        List<TableData> tableData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PopularMoveData popularMoveData1 = allMoves.get(i);
            tableData.add(new TableData(popularMoveData1.getUci(), popularMoveData1.getWhite(),
                    popularMoveData1.getDraws(), popularMoveData1.getBlack()));
        }
        model.addAttribute("tableData", tableData);

        //^
        return "best-moves";
    }


    @RequestMapping(value = "/best-moves", method = RequestMethod.POST, params = {"ruch"})
    public @ResponseBody
    String makeMoveALot(@ModelAttribute Move move, Model model, @RequestParam String ruch) {
        model.addAttribute("activePage", "bestMoves");
        move.setMove(ruch);

        model.addAttribute("move", move);

        boardLogic(move, model);

        skip = true;
        return "best-moves";
    }

    @RequestMapping(value = "/best-moves", method = RequestMethod.POST, params = {"resetowanie"})
    public @ResponseBody
    String makeMoveALotReset(@ModelAttribute Move move, Model model, @RequestParam String resetowanie) {
        model.addAttribute("activePage", "bestMoves");

        if (resetowanie.equals("true")) {
            fenPosition = new FENPosition();
            move = new Move();
            move.setMove("");
            resetowanie = "false";
        }

        model.addAttribute("move", move);

        boardLogic(move, model);

        skip = true;
        return "best-moves";
    }

    // END OF BEST MOVES LICHESS

    // BEST MOVES MASTERS

    public Model boardLogicForGet(Model model){
        model.addAttribute("positionData", mastersPositionService.getDataPosition(fenPosition.getFENPosition()));
        EvaluationModel evaluations = evaluationService.getEvaluations(fenPosition.getFENPosition());
        model.addAttribute("evaluation", evaluations);
        model.addAttribute("evaluationData", new EvaluationData(evaluations));

        model.addAttribute("move", new Move());
        model.addAttribute("fenPosition", fenPosition);

        // tworzenie poczatkowych strzalek
        PositionData positionData = mastersPositionService.getDataPosition(fenPosition.getFENPosition());
        List<PopularMoveData> popularMoveData = positionData.getMoves();
        List<PopularMoveData> allMoves = new ArrayList<>();
        List<String> movesUci = new ArrayList<>();
        for (int i = 0; i < Math.min(popularMoveData.size(), 5); i++) {
            movesUci.add(popularMoveData.get(i).getUci());
            allMoves.add(popularMoveData.get(i));
        }
        if (allMoves.size() < 5) {
            for (int i = allMoves.size(); i < 5; i++) {
                PopularMoveData tmp = new PopularMoveData();
                tmp.setAverageRating(0);
                tmp.setBlack(0);
                tmp.setDraws(0);
                tmp.setUci("");
                tmp.setWhite(0);
                allMoves.add(tmp);
            }
        }
        List<TableData> tableData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PopularMoveData popularMoveData1 = allMoves.get(i);
            tableData.add(new TableData(popularMoveData1.getUci(), popularMoveData1.getWhite(),
                    popularMoveData1.getDraws(), popularMoveData1.getBlack()));
        }
        model.addAttribute("tableData", tableData);
        model.addAttribute("movesUci", movesUci);
        model.addAttribute("popularMoves", allMoves);

        return model;
    }

    @GetMapping("/Masters-moves")
    public String getMastersMoves(Model model) {

        model.addAttribute("activePage", "bestMastersMoves");


        boardLogicForGet(model);
        skip = false;
        return "masters-moves";
    }

    @PostMapping(value = "/Masters-moves", params = {})
    public String makeMastersMove(@ModelAttribute Move move, Model model) {

        model.addAttribute("activePage", "bestMastersMoves");
        model.addAttribute("move", move);


        if (skip) {
            move.setMove("");
            skip = false;
        }

        model = boardLogic(move, model);
        List<PopularMoveData> allMoves = (List) model.getAttribute("popularMoves");


        List<TableData> tableData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PopularMoveData popularMoveData1 = allMoves.get(i);
            tableData.add(new TableData(popularMoveData1.getUci(), popularMoveData1.getWhite(),
                    popularMoveData1.getDraws(), popularMoveData1.getBlack()));
        }
        model.addAttribute("tableData", tableData);

        return "masters-moves";
    }


    @RequestMapping(value = "/Masters-moves", method = RequestMethod.POST, params = {"resetowanie"})
    public @ResponseBody
    String makeMoveALotResetMasters(@ModelAttribute Move move, Model model, @RequestParam String resetowanie) {
        model.addAttribute("activePage", "bestMastersMoves");

        if (resetowanie.equals("true")) {
            fenPosition = new FENPosition();
            move = new Move();
            move.setMove("");
            resetowanie = "false";
        }

        model.addAttribute("move", move);

        boardLogic(move, model);


        skip = true;
        return "masters-moves";
    }


    @RequestMapping(value = "/Masters-moves", method = RequestMethod.POST, params = {"ruch"})
    public @ResponseBody
    String makeMastersMoveALot(@ModelAttribute Move move, Model model, @RequestParam String ruch) {
        model.addAttribute("activePage", "bestMastersMoves");

        move.setMove(ruch);

        model.addAttribute("move", move);

        boardLogic(move, model) ;
        return "masters-moves";
    }

    //END OF BEST MOVES MASTERS


    //EVALUATION ENDPOINTS
    @GetMapping("/evaluation")
    public String evaluation(Model model) {

        model.addAttribute("activePage", "evaluation");

        boardLogicForGet(model);

        return "evaluation";
    }



    @PostMapping(value = "/evaluation", params = {})
    public String evaluation(@ModelAttribute Move move, Model model) {

        if (skip) {
            move.setMove("");
            skip = false;
        }

        model.addAttribute("move", move);
        model.addAttribute("activePage", "evaluation");

        model = boardLogic(move, model);
        List<PopularMoveData> allMoves = (List) model.getAttribute("popularMoves");


        List<TableData> tableData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PopularMoveData popularMoveData1 = allMoves.get(i);
            tableData.add(new TableData(popularMoveData1.getUci(), popularMoveData1.getWhite(),
                    popularMoveData1.getDraws(), popularMoveData1.getBlack()));
        }
        model.addAttribute("tableData", tableData);


        skip = true;
        return "evaluation";
    }

    @RequestMapping(value = "/evaluation", method = RequestMethod.POST, params = {"ruch"})
    public @ResponseBody
    String makeEvaluationMove(@ModelAttribute Move move, Model model, @RequestParam String ruch) {

        move.setMove(ruch);

        model.addAttribute("move", move);

        boardLogic(move, model);
        skip = true;
        return "evaluation";
    }

    @RequestMapping(value = "/evaluation", method = RequestMethod.POST, params = {"resetowanie"})
    public @ResponseBody
    String makeEvaluationReset(@ModelAttribute Move move, Model model, @RequestParam String resetowanie) {

        if (resetowanie.equals("true")) {
            fenPosition = new FENPosition();
            move = new Move();
            move.setMove("");
            resetowanie = "false";
        }

        model.addAttribute("move", move);

        boardLogic(move, model);

        skip = true;
        return "evaluation";
    }

//END OF EVALUATION ENDPOINTS



    @GetMapping("/top-players")
    public String getTopPlayers(Model model) {
        model.addAttribute("activePage", "topPlayers");
        model.addAttribute("topPlayersData", topPlayersService.getTopPlayers());

        return "top-players";
    }


    @GetMapping("/about")
    public String getAbout(Model model) {
        model.addAttribute("activePage", "about");

        return "about";
    }


    public Model boardLogic(Move move, Model model){


        Board board = new Board();
        board.loadFromFen(fenPosition.toString());
        List<com.github.bhlangonijr.chesslib.move.Move> moves = board.legalMoves();

        move = fixCastling(move, board);

        for (com.github.bhlangonijr.chesslib.move.Move moveChessLib : moves) {
            if (moveChessLib.toString().equals(move.getMove())) {
                board.doMove(moveChessLib);
                break;
            }
        }
        fenPosition.setFENPosition(board.getFen());
        EvaluationModel evaluations = evaluationService.getEvaluations(fenPosition.getFENPosition());
        model.addAttribute("evaluation", evaluations);
        model.addAttribute("evaluationData", new EvaluationData(evaluations));

        //" " replacement for %20
        FENPosition temp = new FENPosition();
        temp.setFENPosition(fenPosition.getFENPosition());
        PositionData positionData = positionService.getDataPosition(temp.getFENPosition());
        temp.setFENPosition(temp.getFENPosition().replace(" ", "%20"));
        // ^
        model.addAttribute("fenPosition", temp);
        // getting list of most popular moves (max 5)
        List<PopularMoveData> popularMoveData = positionData.getMoves();
        List<PopularMoveData> allMoves = new ArrayList<>();
        List<String> movesUci = new ArrayList<>();
        for (int i = 0; i < Math.min(popularMoveData.size(), 5); i++) {
            allMoves.add(popularMoveData.get(i));
            movesUci.add(popularMoveData.get(i).getUci());
        }
        if (allMoves.size() < 5) {
            for (int i = allMoves.size(); i < 5; i++) {
                PopularMoveData tmp = new PopularMoveData();
                tmp.setAverageRating(0);
                tmp.setBlack(0);
                tmp.setDraws(0);
                tmp.setUci("");
                tmp.setWhite(0);
                allMoves.add(tmp);
            }
        }

        model.addAttribute("movesUci", movesUci);
        model.addAttribute("popularMoves", allMoves);
        return model;
    }


    public Move fixCastling(Move move, Board board){
        if (board.getPiece(Square.E8).equals(Piece.BLACK_KING)) {
            if (move.getMove().equals("e8h8")) {
                move.setMove("e8g8");
            }
            if (move.getMove().equals("e8a8")) {
                move.setMove("e8c8");
            }
        }
        if (board.getPiece(Square.E1).equals(Piece.WHITE_KING)) {
            if (move.getMove().equals("e1h1")) {
                move.setMove("e1g1");
            }
            if (move.getMove().equals("e1a1")) {
                move.setMove("e1c1");
            }
        }
        return move;
    }

}