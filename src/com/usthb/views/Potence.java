package com.usthb.views;

import java.awt.*;

@SuppressWarnings("serial")
public class Potence extends Component {


    private int state;
    private boolean found;
    private Dimension dimension;

    public Potence(Dimension dimension) {
        this.state = 0;
        this.found = false;
        this.dimension = dimension;
    }


    public void setFound(Boolean found) {
        this.found = found;
    }

    public int getState() {
        return state;
    }

    public void incrementState() {
        this.state ++;
    }
    
    public void resetState() {
    	this.state=0;
    }

    @Override
    public void paint(Graphics g) {

        this.dimension = getSize(); // de Component
        g.clearRect(0, 0, this.dimension.width - 1, this.dimension.height - 90); //effacer
        g.drawRect(0, 0, this.dimension.width - 1, this.dimension.height - 90); //tracer le cadre
        int taille = 12 * (this.dimension.width / 120);
        if (taille < 8) taille = 8;
        g.setFont(new Font("TimesRoman", Font.PLAIN, taille));
        if (state >= 1) g.drawLine(l(30), h(120), l(90), h(120));
        if (state >= 2) g.drawLine(l(30), h(120), l(30), h(40));
        if (state >= 3) g.drawLine(l(60), h(120), l(30), h(90));
        if (state >= 4) g.drawLine(l(30), h(40), l(80), h(40));
        if (state >= 5) g.drawLine(l(30), h(60), l(50), h(40));
        if (state >= 6) g.drawLine(l(70), h(40), l(70), h(60));
        if (state >= 7) g.drawOval(l(65), h(60), l(10), h(10)); // tÃªte
        if (state >= 8) {
            g.drawLine(l(70), h(70), l(70), h(85));
            g.drawLine(l(70), h(70), l(65), h(75)); // corps
            g.drawLine(l(70), h(70), l(75), h(75)); // corps
            g.drawLine(l(70), h(85), l(65), h(95)); // corps
            g.drawLine(l(70), h(85), l(75), h(95)); // corps
        }
        if (this.found) g.drawString("Bravo! vous avez trouvé", l(10), h(150));
        else if (state == 8) g.drawString("Vous étes pendu !", l(10), h(150));
        else if (state == 7)
            g.drawString("Reste un coup à jouer !", l(10), h(150));
        else // (etat >=0 && etat <7)
            g.drawString("Reste "+(8-state)+" coups à jouer", l(10), h(150));
    }

    int l (int v)
    {
        double k = Math.min(this.dimension.width/140., this.dimension.height/160);
        return (int)(v*k);
    }

    int h (int v)
    {
        double k = Math.min(this.dimension.width/140., this.dimension.height/160);
        return (int)(v*k);
    }
}
