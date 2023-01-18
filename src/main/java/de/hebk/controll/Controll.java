package de.hebk.controll;


import de.hebk.Game;
import de.hebk.controll.gui.*;
import de.hebk.gamemode.Gamemode;
import de.hebk.gamemode.ModeJeopardy;
import de.hebk.gamemode.ModeNormal;
import de.hebk.gamemode.ModeSurvive;
import de.hebk.model.list.List;
import de.hebk.user.User;

import javax.swing.*;
import java.util.Random;

public class Controll {

    private Leaderboard leaderboard;
    private Game game;
    private Menu menu;
    private GamemodeSelection gamemodeSelection;
    private Profile profile;
    private Options options;
    private List<String> topics;

    /**
     * Konstruktor
     * @param pGame
     */
    public Controll(Game pGame){
        game = pGame;
        menu = new Menu(this,"Menü");
        gamemodeSelection = new GamemodeSelection(this,"Spielen");
        profile = new Profile(this,"Profile");
        leaderboard = new Leaderboard(this,"Bestenliste");
        options = new Options(this,"Optionen");
        topics = new List<>();
        setTopics(new String[]{"Erdkunde","Fangfrage","Musik","Filme","Naturwissenschaften","Geschichte","Wissenschaft"});
    }

    /**
     * Hiermit wird die GUI des jeweiligen Gamemodes gestartet
     * @param pGamemode
     */
    public void gamemodeGui(Gamemode pGamemode){
        if(pGamemode instanceof ModeNormal) {
            ModenormalGui gui = new ModenormalGui(this,pGamemode, "Wer Wird Millionär");
        }else if(pGamemode instanceof ModeSurvive){
            ModesurviveGui gui1 = new ModesurviveGui(this,pGamemode,"Wie länge überstehst du?");
        }else if(pGamemode instanceof ModeJeopardy){
            if(game.getUser()[1] == null){
                showLogin();
            }else{
                List<String> list = new List<>();
                list = topics;
                String[] arr = new String[4];
                Random rand = new Random();
                for(int i = 0; i < arr.length;i++) {
                    list.toFirst();
                    int j = rand.nextInt(list.getLenght() + 1);
                    for (int l = 0; l < j; l++) {
                        list.next();
                    }
                    arr[i] = list.getObject();
                    list.remove();
                }
                ModejeopardyGui gui = new ModejeopardyGui(this, pGamemode,"Jeopardy",arr,game.getUser()[1]);
            }
        }
    }


    /**
     * öffnet das Menu
     */
    public void showMenu(){
        menu.setVisible(true);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * öffnet die Gamemodeauswahl
     */
    public void showSelection(){
        gamemodeSelection.setVisible(true);
        gamemodeSelection.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * öffnet das Leaderboard
     */
    public void showLeaderboard(){
        leaderboard.setVisible(true);
        leaderboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Öffnet den LogIN Screen
     */
    public void showLogin(){
        LogIn logIn = new LogIn(this,"LogIn");
    }

    public void showProfile() {
        profile.setVisible(true);
        profile.setExtendedState(JFrame.MAXIMIZED_BOTH);
        profile.setText();
    }

    public void showOptions(){
        options.setVisible(true);
        options.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * gibt das Leaderboard zurück
     * @return
     */
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    /**
     * Setzt das Leaderboard
     * @param leaderboard
     */
    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    /**
     * Gibt das Game zurück
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     * setzt das Game
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(String[] arr) {
        for(int i = 0; i<arr.length;i++){
            topics.append(arr[i]);
        }
    }

    public void addTopic(String pTopic){
        topics.append(pTopic);
    }

    public void deleteTopic(String pTopic){
        topics.toFirst();
        for(int i = 0; i<topics.getLenght(); i++){
            if(topics.getObject().equals(pTopic)){
                topics.remove();
                return;
            }
            topics.next();
        }
    }
}
