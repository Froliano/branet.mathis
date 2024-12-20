package branet.mathis.moto;

public class Yamaha extends Moto
{
    public Yamaha(String nom, float puissance, float couple, float poidsAVide, float prix)
    {
        super(nom, puissance, couple, poidsAVide, prix);
    }

    @Override
    protected String rupteur() {
        return "YAAAAAAAMAHA";
    }
}
