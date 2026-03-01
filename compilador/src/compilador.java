import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;

public class compilador {
    private JPanel contentPane;
    private JLabel south;
    private JToolBar north;
    private JSplitPane center;
    private JButton btn_novo;
    private JButton btn_abrir;
    private JButton btn_salvar;
    private JButton btn_copiar;
    private JButton btn_colar;
    private JButton btn_recortar;
    private JButton btn_compilar;
    private JButton btn_equipe;
    private JTextArea textArea1;
    private JTextArea textArea2;

    private String arquivoaAtual;

    public static void main(String[] args) {
        // entrada da aplicação
        JFrame frame = new JFrame("compilador"); // janela principal
        frame.setSize(1500, 800);
        frame.setResizable(false); // não mudar o tamanho
        frame.setContentPane(new compilador().contentPane); // "cria" a tela com o form
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // define oq acontece com a tela ao fechar, EXIT_ON_CLOSE -> encerra o programa
        //frame.pack(); // ajusta a tela automáticamente a partir dos componentes
        frame.setVisible(true); // mostra a janela na tela


    }

    private ImageIcon loadIcon(String path) { // ajustar tamanho dos incos
        ImageIcon icon = new ImageIcon (getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    };

    public compilador() {
        textArea1.setBorder(new NumberedBorder()); // chamndo os números no canto

        // carregando icones e ajustando os botões
        btn_novo.setIcon(loadIcon("icons/novo.png"));
        btn_novo.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_novo.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_abrir.setIcon(loadIcon("icons/abrir.png"));
        btn_abrir.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_abrir.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_salvar.setIcon(loadIcon("icons/salvar.png"));
        btn_salvar.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_salvar.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_copiar.setIcon(loadIcon("icons/copiar.png"));
        btn_copiar.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_copiar.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_colar.setIcon(loadIcon("icons/colar.png"));
        btn_colar.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_colar.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_recortar.setIcon(loadIcon("icons/recortar.png"));
        btn_recortar.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_recortar.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_compilar.setIcon(loadIcon("icons/compilar.png"));
        btn_compilar.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_compilar.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_equipe.setIcon(loadIcon("icons/equipe.png"));
        btn_equipe.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_equipe.setVerticalTextPosition(SwingConstants.BOTTOM);

        // ação dos botões
        btn_novo.addActionListener(e -> acao_btn_novo());
        contentPane.registerKeyboardAction(e -> btn_novo.doClick(), KeyStroke.getKeyStroke("control N"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        btn_abrir.addActionListener(e -> acao_btn_abrir());
        contentPane.registerKeyboardAction(e -> btn_abrir.doClick(), KeyStroke.getKeyStroke("control O"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        btn_salvar.addActionListener(e -> acao_btn_salvar());
        contentPane.registerKeyboardAction(e -> btn_salvar.doClick(), KeyStroke.getKeyStroke("control S"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        // copiar, colar e recortar
        btn_copiar.addActionListener(e -> textArea1.copy());
        btn_colar.addActionListener(e -> textArea1.paste());
        btn_recortar.addActionListener(e -> textArea1.cut());

        btn_compilar.addActionListener(e -> acao_btn_compilar());
        contentPane.registerKeyboardAction(e -> btn_compilar.doClick(), KeyStroke.getKeyStroke("F7"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        btn_equipe.addActionListener(e -> acao_btn_equipe());
        contentPane.registerKeyboardAction(e -> btn_equipe.doClick(), KeyStroke.getKeyStroke("F1"), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void acao_btn_novo (){
        textArea1.setText("");
        textArea2.setText("");
        south.setText("");
    };

    private void acao_btn_abrir(){
        JFileChooser achar_txt = new JFileChooser();
        achar_txt.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));

        int resultado = achar_txt.showOpenDialog(contentPane);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            java.io.File file = achar_txt.getSelectedFile();

            try {
                java.nio.file.Path path = file.toPath();
                String conteudo = java.nio.file.Files.readString(path);
                textArea1.setText(conteudo);
                textArea2.setText("");
                south.setText(file.getAbsolutePath());
                arquivoaAtual = file.getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void acao_btn_salvar(){
        try {
            // aquivo novo
            if (arquivoaAtual == null) {
                JFileChooser salvar_txt = new JFileChooser();
                salvar_txt.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));
                int resultado = salvar_txt.showOpenDialog(contentPane);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    arquivoaAtual = String.valueOf(salvar_txt.getSelectedFile());

                    java.nio.file.Files.writeString(Path.of(arquivoaAtual), textArea1.getText()); // ver para salvar o arquivo como .txt por padrão

                    textArea2.setText("");
                    south.setText(arquivoaAtual);
                }
            } else {
                java.nio.file.Files.writeString(Path.of(arquivoaAtual), textArea1.getText());
                textArea2.setText("");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void acao_btn_compilar(){
        textArea2.setText(""); // apaga o q tinha antes por precaução
        textArea2.setText("compilação de programas ainda não foi implementada");
    }

    private void acao_btn_equipe(){
        textArea2.setText(""); // apaga o q tinha antes por precaução
        textArea2.setText("Equipe: Arthur Utpadel e Gustavo Westphal Antunes");
    }
}
