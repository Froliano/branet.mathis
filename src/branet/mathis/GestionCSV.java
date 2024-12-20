package branet.mathis;

import branet.mathis.moto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestionCSV
{
    private final static String FILE_PATH = "./garage.csv";

    public static List<Moto> read() {
        List<Moto> moto = null;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(FILE_PATH));
            List<Moto> motoTmp = new ArrayList<Moto>(allLines.size());
            allLines.forEach(line -> motoTmp.add(Moto.transformCsvToMoto(line)));
            moto = motoTmp;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return moto;
    }

    public static void write(List<Moto> motos) {

        List<String> lines = new ArrayList<String>();
        for(Moto moto : motos)
        {
            String csv = "";
            if (moto instanceof Yamaha)
                csv += "Yamaha;";
            else if (moto instanceof Honda)
                csv += "Honda;";
            else if (moto instanceof Kawasaki)
                csv += "Kawasaki;";
            else if (moto instanceof Ducati)
                csv += "Ducati;";
            csv += moto.toCSV();
            lines.add(csv);
        }
        try {
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
