package branet.mathis.moto;

public abstract class Moto
{
    private String nom;
    private float puissance;
    private float couple;
    private float poidsAVide;
    private  float prix;

    protected Moto(String nom, float puissance, float couple, float poidsAVide, float prix)
    {
        this.nom = nom;
        this.puissance = puissance;
        this.couple = couple;
        this.poidsAVide = poidsAVide;
        this.prix = prix;
    }

    protected abstract String rupteur();

    public String getNom() {
        return nom;
    }

    public float getPuissance() {
        return puissance;
    }

    public float getCouple() {
        return couple;
    }

    public float getPoidsAVide() {
        return poidsAVide;
    }

    public float getPrix() {
        return prix;
    }

    public static Moto transformCsvToMoto(String line) {
        Moto moto = null;
        String[] elems = line.split(";");
        switch (elems[0]) {
            case "Yamaha":
                moto = new Yamaha(elems[1], Float.parseFloat(elems[2]), Float.parseFloat(elems[3]), Float.parseFloat(elems[4]), Float.parseFloat(elems[5]));
                break;
            case "Honda":
                moto = new Honda(elems[1], Float.parseFloat(elems[2]), Float.parseFloat(elems[3]), Float.parseFloat(elems[4]), Float.parseFloat(elems[5]));
                break;
            case "Kawazaki":
                moto = new Kawasaki(elems[1], Float.parseFloat(elems[2]), Float.parseFloat(elems[3]), Float.parseFloat(elems[4]), Float.parseFloat(elems[5]));
                break;
            case "Ducati":
                moto = new Ducati(elems[1], Float.parseFloat(elems[2]), Float.parseFloat(elems[3]), Float.parseFloat(elems[4]), Float.parseFloat(elems[5]));
                break;
        }
        return moto;
    }

    public String toCSV()
    {
        return nom + ";" + puissance + ";" + couple + ";" + poidsAVide + ";" + prix;
    }
}
