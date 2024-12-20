package branet.mathis.moto;

public class Ducati extends Moto
{
    public Ducati(String nom, float puissance, float couple, float poidsAVide, float prix) {
        super(nom, puissance, couple, poidsAVide, prix);
    }

    @Override
    protected String rupteur() {
        return "DUCATIIIII";
    }
}
