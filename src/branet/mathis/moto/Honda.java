package branet.mathis.moto;

public class Honda extends Moto
{
    public Honda(String nom, float puissance, float couple, float poidsAVide, float prix)
    {
        super(nom, puissance, couple, poidsAVide, prix);
    }

    @Override
    protected String rupteur() {
        return "HOOOOONDA";
    }
}
