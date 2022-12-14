import java.util.ArrayList;
import java.util.Observable;
import Console.Event;


public class Line extends Observable {

    private int cursor;
    private ArrayList<Character> buffer_line;
    private boolean insert;
    private Event ev;

    public Line() {
        cursor = 0;
        insert = false; //por convenio
        buffer_line = new ArrayList<Character>();
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < this.buffer_line.size(); i++) {
            res += this.buffer_line.get(i);
        }
        return res;
    }

    public void moveto_right() {
        int aux = this.cursor;
        if (this.cursor < this.buffer_line.size()) {
            this.cursor++;
            ev = new Event(Dicc.t_RIGHT, true);

        } else {
            ev = new Event(Dicc.t_RIGHT, false);
        }

        this.setChanged();
        this.notifyObservers(ev);

    }

    public void moveto_left() {
        if (this.cursor != 0) {
            this.cursor--;

        }
        ev = new Event(Dicc.t_LEFT);
        this.setChanged();
        this.notifyObservers(ev);

    }

    public void moveto_home() {
        int tmp = this.cursor;
        this.cursor = 0;
        ev = new Event(Dicc.t_HOME, tmp);
        this.setChanged();
        this.notifyObservers(ev);

    }

    public void moveto_end() {

        int tmp = this.cursor;
        this.cursor = this.buffer_line.size();

        ev = new Event(Dicc.t_END, (this.cursor - tmp));

        this.setChanged();
        this.notifyObservers(ev);

    }

    public void insert() {
        this.insert = !this.insert;
        ev = new Event(Dicc.t_INTRO);
        this.setChanged();
        this.notifyObservers(ev);
    }

    public void delete() {
        try {
            if (this.cursor > 0) {
                this.buffer_line.remove(this.cursor - 1);
                this.cursor--;
                ev = new Event(Dicc.DEL);

                this.setChanged();
                this.notifyObservers(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("error en delete");
        }

    }

    public void suprimir() {
        try {
            if (this.cursor < this.buffer_line.size()) {
                this.buffer_line.remove(this.cursor);
                if (this.cursor != 0) {
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("error en suprimir");

        }
        ev = new Event(Dicc.t_SUP);
        this.setChanged();
        this.notifyObservers(ev);
    }

    public void add(char letra) {
        if (this.insert) {
            if (this.cursor < this.buffer_line.size()) {
                this.buffer_line.set(this.cursor, letra);
            } else {
                this.buffer_line.add(this.cursor, letra);
            }
            this.cursor++;
            ev = new Event(Dicc.t_ADD, true, letra);
            this.setChanged();
            this.notifyObservers(ev);

        } else {
            this.buffer_line.add(this.cursor, letra);
            this.cursor++;
            ev = new Event(Dicc.t_ADD, false, letra);
            this.setChanged();
            this.notifyObservers(ev);
        }

    }

}