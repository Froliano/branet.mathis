package branet.mathis.moto;

public class Kawasaki extends Moto
{
    public Kawasaki(String nom, float puissance, float couple, float poidsAVide, float prix) {
        super(nom, puissance, couple, poidsAVide, prix);
    }

    @Override
    protected String rupteur() {
        return "KAAWAASAAKIIIII";
    }
}
