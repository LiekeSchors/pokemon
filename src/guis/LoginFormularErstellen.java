package guis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import datenbank.DatenbankVerbindung;
import views.PokemonKartenView;

public class LoginFormularErstellen extends JFrame {
    private JLabel benutzerNameLabel, passWortLabel;
    private JTextField benutzerNameTextField;
    private JPasswordField passWortField;
    private JButton anmeldenButton;
    private JPanel panel;

    public LoginFormularErstellen() {
        setTitle("Login Formular");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 500));

        passWortField.setEchoChar('♬');

        anmeldenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });


        benutzerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        passWortField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    // Method to perform login action
    private void login() {
        String benutzername = benutzerNameTextField.getText();
        String passwort = new String(passWortField.getPassword());

        benutzer = getAuthentifizierterBenutzer(benutzername, passwort);

        if (benutzer != null) {
            PokemonKartenView pokemonKartenView = new PokemonKartenView();
            setVisible(false);
            pokemonKartenView.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(LoginFormularErstellen.this, "E-Mail oder Passwort nicht korrekt.",
                    "Versuche es noch einmal", JOptionPane.ERROR_MESSAGE);
        }



    }

    public Benutzer benutzer;

    private Benutzer getAuthentifizierterBenutzer(String email, String passwort) {
        Benutzer benutzer = null;
        Connection con = DatenbankVerbindung.connectDB();
        PreparedStatement p;
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT email FROM benutzer WHERE email =? AND passwort = crypt(?, passwort)";
            p = con.prepareStatement(sql);
            p.setString(1, email);
            p.setString(2, passwort);

            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                benutzer = new Benutzer();
                benutzer.email = rs.getString("email");
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return benutzer;

    }

    public static void main(String[] args) {
        try {
            LoginFormularErstellen login = new LoginFormularErstellen();
            login.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
