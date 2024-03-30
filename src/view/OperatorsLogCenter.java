package view;

import javax.swing.*;

public class OperatorsLogCenter extends JPanel{

    private JTextArea text;
    private JScrollPane scrollBar;
    public OperatorsLogCenter(){
        this.text = new JTextArea(10,45);
        this.scrollBar = new JScrollPane(text);
        add(scrollBar);
    }

    public void addLog(String log){
        text.setText(text.getText() + log);
    }



    /*
    private List<CustomButton> buttons = new ArrayList<>();

    public OperatorsCenterPanel(){
        setVisible(true);
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            CustomButton button = new CustomButton(i,"Operator " + i);
            buttons.add(button);
            add(button);
        }
    }
    public void setBusy(int id){
        buttons.get(id).setBackground(Color.RED);

    }
    public void setFree(int id){
        buttons.get(id).setBackground(Color.GREEN);
    }

    class CustomButton extends JButton{
        private int id;
        public CustomButton(int id, String name){
            super(name);
            this.id = id;
        }
        public int getId(){
            return id;
        }
    }

     */


}
