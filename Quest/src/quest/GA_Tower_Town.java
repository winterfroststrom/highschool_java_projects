/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class GA_Tower_Town extends GrandArea{
    A_TT_1_1_1 a111;
    A_TT_1_2_1 a121;
    A_TT_1_3_1 a131;
    A_TT_1_2_2 a122;

    public GA_Tower_Town(Game game){
        super(game);
        preArea = 0x00000;
    }

    @Override
    public void load() {
        a111 = new A_TT_1_1_1(game);
        a121 = new A_TT_1_2_1(game);
        a131 = new A_TT_1_3_1(game);
        a122 = new A_TT_1_2_2(game);
    }

    @Override
    public Area getAreaById(int id) {
        switch(id){
            case 0x00111: return a111;
            case 0x00121: return a121;
            case 0x00122: return a122;
            case 0x00131: return a131;
            default: return null;
        }
    }

    @Override
    public void die() {
        preArea = 0x00000;
        current = a111;
        current.loadArea();
    }

    @Override
    public void setCurrentById(int id) {
        switch(id){
            case 0x00000: current = a111;
                break;
            case 0x01131: current = a122;
                break;
            case 0x02111: current = a111;
                break;
            default: current = null;
                break;
        }
    }
}
