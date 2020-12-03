package Restaurant;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Windows extends JFrame {
    Item apple = new Dish("Яблоко", "зеленое", 5);
    Item juice = new Drink("Сок", "из малины", 15);
    Item chicken = new Dish("Курица", "жареная", 30);
    Order[] orders = new Order[100];
    int count = 0;
    OrderManager manager= new OrderManager();
    int table = 0;
    JLabel tablewarning = new JLabel(" ");

    JTextField addressName = new JTextField(20);
    JTextField addressCity = new JTextField(20);
    JTextField addressAvenue = new JTextField(20);
    JTextField addressHouse = new JTextField(5);

    JPanel[] panel = new JPanel[20];
    JLabel textTavern = new JLabel("Таверна <Под кудлатым мишкой>");
    JLabel textWelcome = new JLabel("Добро пожаловать!");
    JButton tableOrder = new JButton("Заказ со столиком");
    JButton typeTable = new JButton("Добавить столик");
    JButton internetOrder = new JButton("Заказ на дом");
    JButton typeAddress = new JButton("Добавить адрес");
    JLabel help = new JLabel("По порядку: имя, город, адрес, дом");
    JLabel canOrder = new JLabel("В наличии:");
    JButton apl = new JButton("Яблоко");
    JButton juc = new JButton("Сок");
    JButton chi = new JButton("Курица");
    JButton getOrder = new JButton("Добавить заказ");
    String[] itemsOrder = new String[1];
    int appleflag = 0;
    int juiceflag = 0;
    int chickenflag = 0;
    double costTotalOrder = 0;
    JLabel costTotal = new JLabel("Всего: " + costTotalOrder + " оренов");
    JLabel warning1 = new JLabel("Сначала выберете:");
    JLabel warning2 = new JLabel("за столиком или домой!");
    int flag_internet_table = 0;
    int flagAddress = 0;
    JLabel flag = new JLabel("Вариант заказа не выбран");

    JList listItems = new JList(itemsOrder);

    Windows(){
        super("Таверна <Под кудлатым мишкой>");
        setSize(1100,600);
        setLayout(new GridLayout(4,5));

        table = manager.freeTableNumber();

        Font font = new Font("TimesRoman",Font.BOLD,20);
        textWelcome.setFont(font);
        font = new Font("TimesRoman",Font.BOLD,16);
        canOrder.setFont(font);
        warning1.setFont(font);
        warning1.setForeground(Color.red);
        warning2.setForeground(Color.red);

        for(int i =0; i < 20; i++){
            panel[i] = new JPanel();
            add(panel[i]);
        }

        itemsOrder[0] ="Список заказанного:";

        panel[0].setBackground(new Color(200,200,200));
        panel[1].setBackground(new Color(200,200,200));
        panel[2].setBackground(new Color(200,200,200));
        panel[3].setBackground(new Color(200,200,200));
        panel[4].setBackground(new Color(200,200,200));
        panel[8].setBackground(new Color(100,100,150));
        panel[9].setBackground(new Color(50,200,50));
        panel[13].setBackground(new Color(200,10,100));
        panel[14].setBackground(new Color(200,120,50));
        panel[18].setBackground(new Color(150,150,150));
        panel[19].setBackground(new Color(150,150,150));


        panel[0].setLayout(new BorderLayout());
        panel[0].add(textTavern, BorderLayout.NORTH);
        panel[2].setLayout(new BorderLayout());
        panel[2].add(textWelcome, BorderLayout.CENTER);
        panel[4].setLayout(new FlowLayout());
        panel[4].add(warning1);
        panel[4].add(warning2);

        panel[5].setLayout(new FlowLayout());
        panel[5].add(listItems);

        panel[6].setLayout(new FlowLayout());
        panel[7].setLayout(new FlowLayout());
        panel[6].add(tableOrder);
        panel[6].add(typeTable);
        panel[7].add(internetOrder);
        panel[7].add(typeAddress);
        panel[7].add(help);
        panel[12].setLayout(new FlowLayout());
        panel[12].add(flag);

        panel[8].setLayout(new FlowLayout());
        panel[8].add(canOrder);

        panel[9].setLayout(new FlowLayout());
        panel[9].add(apl);
        panel[13].setLayout(new FlowLayout());
        panel[13].add(juc);
        panel[14].setLayout(new FlowLayout());
        panel[14].add(chi);

        panel[16].setLayout(new FlowLayout());
        panel[16].add(costTotal);
        panel[17].setLayout(new BorderLayout());
        panel[17].add(getOrder, BorderLayout.CENTER);

        panel[11].setLayout(new FlowLayout());
        panel[10].setLayout(new FlowLayout());


        tableOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(orders[count] == null) {
                    orders[count] = new RestaurantOrder();
                    flag_internet_table = 1;
                    flag.setText("Выбран заказ в ресторане");
                }
            }
        });

        typeTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag_internet_table == 1){
                    int[] tables = manager.freeTableNumbers();
                    int rand = (int) (Math.random() * tables.length);
                    table = tables[rand];
                    tablewarning.setText("Ваш столик - " + table);
                    panel[11].removeAll();
                    panel[11].add(tablewarning);
                    panel[11].revalidate();
                    panel[11].repaint();
                }
            }
        });

        internetOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(orders[count] == null) {
                    orders[count] = new InternetOrder();
                    flag_internet_table = 2;
                    flag.setText("Выбрана доставка на дом");
                }
            }
        });

        typeAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag_internet_table == 2){
                    panel[11].removeAll();
                    addressName.setText("");
                    addressCity.setText("");
                    addressAvenue.setText("");
                    addressHouse.setText("");
                    panel[11].add(addressName);
                    panel[11].add(addressCity);
                    panel[11].add(addressAvenue);
                    panel[11].add(addressHouse);
                    panel[11].revalidate();
                    panel[11].repaint();
                    flagAddress = 1;
                } else{
                    System.out.println("Nope");
                }
            }
        });

        apl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag_internet_table != 0) {
                    orders[count].add(apple);
                    if(appleflag == 0){
                        appleflag = 1;
                        String[] temp = new String[itemsOrder.length + 1];
                        for (int i = 0; i < itemsOrder.length; i++) {
                            temp[i] = itemsOrder[i];
                        }
                        temp[itemsOrder.length] = "Яблоко: х" + orders[count].itemQuantity("Яблоко");
                        itemsOrder = temp;
                    } else {
                        int zero = -1;
                        for (int j = 0; j < itemsOrder.length; j++) {
                            if (itemsOrder[j].contains("Яблоко")) {
                                zero = j;
                            }
                        }
                        itemsOrder[zero] = "Яблоко: х" + orders[count].itemQuantity("Яблоко");
                    }

                    listItems = new JList(itemsOrder);
                    panel[5].removeAll();
                    panel[5].add(listItems);
                    panel[5].revalidate();

                    costTotalOrder = orders[count].costTotal();
                    costTotal.setText("Всего: " + costTotalOrder + " оренов");
                    panel[16].removeAll();
                    panel[16].add(costTotal);
                    panel[16].revalidate();
                }
            }
        });

        juc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag_internet_table != 0) {
                    orders[count].add(juice);
                    if(juiceflag == 0){
                        juiceflag = 1;
                        String[] temp = new String[itemsOrder.length + 1];
                        for (int i = 0; i < itemsOrder.length; i++) {
                            temp[i] = itemsOrder[i];
                        }
                        temp[itemsOrder.length] = "Сок: х" + orders[count].itemQuantity("Сок");
                        itemsOrder = temp;
                    } else {
                        int zero = -1;
                        for (int j = 0; j < itemsOrder.length; j++) {
                            if (itemsOrder[j].contains("Сок")) {
                                zero = j;
                            }
                        }
                        itemsOrder[zero] = "Сок: х" + orders[count].itemQuantity("Сок");
                    }

                    listItems = new JList(itemsOrder);
                    panel[5].removeAll();
                    panel[5].add(listItems);
                    panel[5].revalidate();

                    costTotalOrder = orders[count].costTotal();
                    costTotal.setText("Всего: " + costTotalOrder + " оренов");
                    panel[16].removeAll();
                    panel[16].add(costTotal);
                    panel[16].revalidate();
                }
            }
        });

        chi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag_internet_table != 0) {
                    orders[count].add(chicken);
                    if(chickenflag == 0){
                        chickenflag = 1;
                        String[] temp = new String[itemsOrder.length + 1];
                        for (int i = 0; i < itemsOrder.length; i++) {
                            temp[i] = itemsOrder[i];
                        }
                        temp[itemsOrder.length] = "Курица: х" + orders[count].itemQuantity("Курица");
                        itemsOrder = temp;
                    } else {
                        int zero = -1;
                        for (int j = 0; j < itemsOrder.length; j++) {
                            if (itemsOrder[j].contains("Курица")) {
                                zero = j;
                            }
                        }
                        itemsOrder[zero] = "Курица: х" + orders[count].itemQuantity("Курица");
                    }

                    listItems = new JList(itemsOrder);
                    panel[5].removeAll();
                    panel[5].add(listItems);
                    panel[5].revalidate();

                    costTotalOrder = orders[count].costTotal();
                    costTotal.setText("Всего: " + costTotalOrder + " оренов");
                    panel[16].removeAll();
                    panel[16].add(costTotal);panel[16].revalidate();
                }
            }
        });

        getOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag_internet_table!=0 &&(table!=0 || flagAddress!=0) && costTotalOrder!=0){
                    if(flag_internet_table == 1){
                        try{
                            manager.add(orders[count], table);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    } else{
                        Address newAddress = new Address(addressName.getText(), addressCity.getText(), addressAvenue.getText(), addressHouse.getText());
                        try{
                            manager.add(orders[count], newAddress);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    panel[11].removeAll();
                    double all = costTotalOrder;
                    flag_internet_table = 0;
                    table = 0;
                    flagAddress = 0;
                    costTotalOrder = 0;
                    appleflag = 0;
                    juiceflag = 0;
                    chickenflag = 0;
                    flag.setText("Вариант заказа не выбран");
                    itemsOrder = new String[1];
                    itemsOrder[0] ="Список заказанного:";
                    listItems = new JList(itemsOrder);
                    panel[5].removeAll();
                    panel[5].add(listItems);
                    panel[5].revalidate();
                    costTotal.setText("Всего: " + costTotalOrder + " оренов");
                    panel[16].removeAll();
                    panel[16].add(costTotal);
                    panel[16].revalidate();
                    if(count <99) {
                        count++;
                    } else{
                        count = 0;
                    }

                    panel[5].repaint();
                    panel[11].repaint();

                    JOptionPane.showMessageDialog(null, "Ваш заказ начал готовится! К оплате: " + all + " оренов");
                }
            }
        });
    }


    public static void main(String[]args)
    {
        new Windows().setVisible(true);
    }
}
