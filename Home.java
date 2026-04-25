import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    public Home(){
        setTitle("Home");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Balance
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=5;
        JLabel balanceLabel = new JLabel("Balance");
        panel.add(balanceLabel,gbc);
    }
}
