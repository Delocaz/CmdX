package me.delocaz.cmdx.api;

public class CXAPI {

    private CXCommandManager cmdman;
    private CXLang lang;
    private CXPlayerDataManager pdman;

    public CXAPI() {
        this.cmdman = new CXCommandManager();
        this.lang = new CXLang();
        this.pdman = new CXPlayerDataManager();
    }

    /**
     * Gets CmdX's command manager
     *
     * @return The CmdX command manager
     */
    public CXCommandManager getCommandManager() {
        return cmdman;
    }
    /**
     * Gets CmdX's language (localization) manager
     * @return  The CmdX language manager
     */
    public CXLang getLanguageManager() {
        return lang;
    }
    /**
     * Gets CmdX's player data manager
     *
     * @return The CmdX player data manager
     */
    public CXPlayerDataManager getPlayerDataManager() {
        return pdman;
    }
}