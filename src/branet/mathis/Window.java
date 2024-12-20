package branet.mathis;

import branet.mathis.moto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Window
{

    private final JFrame frame = new JFrame("Location Moto");

    private List<Moto> garage = new ArrayList<>();

    private java.util.List<Yamaha> yamahaMotos;
    private java.util.List<Honda> hondaMotos;
    private java.util.List<Ducati> ducatiMotos;
    private List<Kawasaki> kawasakiMotos;

    public Window()
    {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMotos();

        garage = GestionCSV.read();
    }

    public void initMotos()
    {
        yamahaMotos = new ArrayList<>();
        yamahaMotos.add(new Yamaha("R1", 200, 115, 200, 15000));
        yamahaMotos.add(new Yamaha("MT-09", 115, 93, 189, 9000));
        yamahaMotos.add(new Yamaha("FZ1", 150, 106, 210, 11000));
        yamahaMotos.add(new Yamaha("XSR900", 118, 87, 195, 9500));

        hondaMotos = new ArrayList<>();
        hondaMotos.add(new Honda("CBR1000RR", 190, 114, 195, 14500));
        hondaMotos.add(new Honda("CB650R", 94, 64, 189, 9000));
        hondaMotos.add(new Honda("CRF450L", 53, 70, 134, 10500));

        ducatiMotos = new ArrayList<>();
        ducatiMotos.add(new Ducati("Panigale V4", 214, 124, 198, 22000));
        ducatiMotos.add(new Ducati("Monster 821", 109, 86, 179, 12000));
        ducatiMotos.add(new Ducati("Hypermotard 950", 114, 96, 193, 16000));

        kawasakiMotos = new ArrayList<>();
        kawasakiMotos.add(new Kawasaki("ZX-10R", 200, 114, 207, 17000));
        kawasakiMotos.add(new Kawasaki("Z900", 125, 98, 210, 9500));
        kawasakiMotos.add(new Kawasaki("Ninja 400", 49, 38, 168, 6000));
        kawasakiMotos.add(new Kawasaki("Versys 1000", 120, 102, 235, 12000));
    }

    public void mainWindow()
    {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        frame.add(panel);

        JButton ajout = new JButton("Acheter une moto");
        ajout.addActionListener(e -> choixMoto());
        panel.add(ajout);

        JButton affichage = new JButton("Affiche ton garage");
        affichage.addActionListener(e -> afficherGarage());
        panel.add(affichage);

        JButton quitter = new JButton("Quitter");
        quitter.addActionListener(e -> quitterApplication());
        panel.add(quitter);
        frame.setVisible(true);

    }

    private void choixMoto()
    {
        frame.getContentPane().removeAll();

        JLabel message = new JLabel("Choisissez une marque de moto !");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(message, BorderLayout.NORTH);

        JPanel panelMarques = new JPanel();
        panelMarques.setLayout(new GridLayout(4, 1));
        frame.add(panelMarques);

        JButton yamaha = new JButton("Yamaha");
        yamaha.addActionListener(e -> choisirYamaha());
        panelMarques.add(yamaha);

        JButton honda = new JButton("Honda");
        honda.addActionListener(e -> choisirHonda());
        panelMarques.add(honda);

        JButton ducati = new JButton("Ducati");
        ducati.addActionListener(e -> choisirDucati());
        panelMarques.add(ducati);

        JButton kawasaki = new JButton("Kawasaki");
        kawasaki.addActionListener(e -> choisirKawasaki());
        panelMarques.add(kawasaki);

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(e -> mainWindow());
        frame.getContentPane().add(retourButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void acheterMoto(Moto moto)
    {
        garage.add(moto);
        GestionCSV.write(garage);
        mainWindow();
    }

    private void afficherGarage()
    {
        // Vérifier si le garage est vide
        if (garage.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Votre garage est vide !");
            return;
        }

        // Effacer le contenu précédent
        frame.getContentPane().removeAll();

        // Créer un panneau principal
        JPanel panelGarage = new JPanel();
        panelGarage.setLayout(new BorderLayout());
        frame.getContentPane().add(panelGarage);

        // Titre
        JLabel titre = new JLabel("Voici votre garage :");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        panelGarage.add(titre, BorderLayout.NORTH);

        // Panneau pour les motos
        JPanel listeMotosPanel = new JPanel();
        listeMotosPanel.setLayout(new GridLayout(garage.size(), 1)); // Une ligne par moto
        panelGarage.add(new JScrollPane(listeMotosPanel), BorderLayout.CENTER);

        // Ajouter chaque moto sous forme de grille
        for (Moto moto : garage) {
            // Créer un sous-panneau pour chaque moto
            JPanel motoPanel = new JPanel();
            motoPanel.setLayout(new GridLayout(1, 4)); // Quatre colonnes : nom, caractéristiques, supprimer, modifier

            // Ajouter le nom de la moto
            JLabel motoLabel = new JLabel(moto.getNom());
            motoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            motoPanel.add(motoLabel);

            // Bouton "Caractéristiques"
            JButton btnCaractéristiques = new JButton("Caractéristiques");
            btnCaractéristiques.addActionListener(e -> afficherFicheProduit(moto, "Garage"));
            motoPanel.add(btnCaractéristiques);

            // Bouton "Supprimer"
            JButton btnSupprimer = new JButton("Supprimer");
            btnSupprimer.addActionListener(e -> {
                garage.remove(moto);
                GestionCSV.write(garage);
                afficherGarage();
            });
            motoPanel.add(btnSupprimer);

            // Bouton "Modifier"
            JButton btnModifier = new JButton("Modifier");
            //btnModifier.addActionListener(e -> modifierMoto(frame, moto));
            motoPanel.add(btnModifier);

            // Ajouter le panneau de la moto au panneau principal
            listeMotosPanel.add(motoPanel);
        }

        // Bouton retour
        JButton boutonRetour = new JButton("Retour");
        boutonRetour.addActionListener(e -> mainWindow());
        panelGarage.add(boutonRetour, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    private void quitterApplication()
    {
        frame.dispose();
        System.exit(0);
    }

    public void afficherFicheProduit(Moto moto, String marque)
    {
        frame.getContentPane().removeAll();

        JLabel titreMoto = new JLabel("Fiche produit: " + moto.getNom());
        titreMoto.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titreMoto, BorderLayout.NORTH);

        JPanel panelCaracteristiques = new JPanel();
        panelCaracteristiques.setLayout(new GridLayout(4, 1));
        frame.add(panelCaracteristiques);

        panelCaracteristiques.add(new JLabel("Puissance: " + moto.getPuissance() + " CV"));
        panelCaracteristiques.add(new JLabel("Couple: " + moto.getCouple() + " Nm"));
        panelCaracteristiques.add(new JLabel("Poids à vide: " + moto.getPoidsAVide() + " kg"));
        panelCaracteristiques.add(new JLabel("Prix: " + moto.getPrix() + " €"));

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new GridLayout(1, 2));

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (marque)
                {
                    case "Yamaha":
                        choisirYamaha();
                        break;
                    case "Ducati":
                        choisirDucati();
                        break;
                    case "Kawasaki":
                        choisirKawasaki();
                        break;
                    case "Honda":
                        choisirHonda();
                        break;
                    case "Garage":
                        afficherGarage();
                        break;
                }
            }
        });
        panelBoutons.add(retourButton);

        JButton acheterButton = new JButton("Acheter");
        acheterButton.addActionListener(e -> acheterMoto(moto));
        panelBoutons.add(acheterButton);

        frame.add(panelBoutons, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void choisirYamaha()
    {
        frame.getContentPane().removeAll();

        JLabel message = new JLabel("Choisissez votre moto Yamaha !");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(message, BorderLayout.NORTH);

        JPanel panelYamaha = new JPanel();
        panelYamaha.setLayout(new GridLayout(4, 1));
        frame.getContentPane().add(panelYamaha, BorderLayout.CENTER);

        for (Moto moto : yamahaMotos) {
            JButton motoButton = new JButton(moto.getNom());
            motoButton.addActionListener(e -> afficherFicheProduit(moto, "Yamaha"));
            panelYamaha.add(motoButton);
        }

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(e -> choixMoto());
        frame.getContentPane().add(retourButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void choisirHonda()
    {
        frame.getContentPane().removeAll();

        JLabel message = new JLabel("Choisissez votre moto Honda !");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(message, BorderLayout.NORTH);

        JPanel panelHonda = new JPanel();
        panelHonda.setLayout(new GridLayout(3, 1));
        frame.getContentPane().add(panelHonda, BorderLayout.CENTER);

        for (Moto moto : hondaMotos) {
            JButton motoButton = new JButton(moto.getNom());
            motoButton.addActionListener(e -> afficherFicheProduit(moto, "Honda"));
            panelHonda.add(motoButton);
        }

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(e -> choixMoto());
        frame.getContentPane().add(retourButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void choisirDucati()
    {
        frame.getContentPane().removeAll();

        JLabel message = new JLabel("Choisissez votre moto Ducati !");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(message, BorderLayout.NORTH);

        JPanel panelDucati = new JPanel();
        panelDucati.setLayout(new GridLayout(3, 1));
        frame.getContentPane().add(panelDucati, BorderLayout.CENTER);

        for (Moto moto : ducatiMotos) {
            JButton motoButton = new JButton(moto.getNom());
            motoButton.addActionListener(e -> afficherFicheProduit(moto, "Ducati"));
            panelDucati.add(motoButton);
        }

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(e -> choixMoto());
        frame.getContentPane().add(retourButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void choisirKawasaki()
    {
        frame.getContentPane().removeAll();

        JLabel message = new JLabel("Choisissez votre moto Kawasaki !");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(message, BorderLayout.NORTH);

        JPanel panelKawasaki = new JPanel();
        panelKawasaki.setLayout(new GridLayout(4, 1));
        frame.getContentPane().add(panelKawasaki, BorderLayout.CENTER);

        for (Moto moto : kawasakiMotos) {
            JButton motoButton = new JButton(moto.getNom());
            motoButton.addActionListener(e -> afficherFicheProduit(moto, "Kawasaki"));
            panelKawasaki.add(motoButton);
        }

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(e -> choixMoto());
        frame.getContentPane().add(retourButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
