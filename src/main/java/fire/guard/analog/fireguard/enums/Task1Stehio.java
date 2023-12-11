package fire.guard.analog.fireguard.enums;

public enum Task1Stehio {
    ACETYLENE(2,2),
    BUTANE(4,10),
    BUTENE(4,8),
    METHANE(1,4),
    PROPYLENE(3,6),
    ETHAN(2,6),
    ETHYLENE(2,4);
    private Integer Nc;
    private Integer Nh;

    public Integer getNc() {
        return Nc;
    }

    public void setNc(Integer nc) {
        Nc = nc;
    }

    public Integer getNh() {
        return Nh;
    }

    public void setNh(Integer nh) {
        Nh = nh;
    }

    Task1Stehio(Integer Nc, Integer Nh){
        this.Nc = Nc;
        this.Nh = Nh;
    }

}
