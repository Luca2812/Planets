import java.awt.Graphics2D;

public class Planet {
    public Vector pos;
    public Vector vel;
    public Vector acc;
    public int mass;

    public Planet(Vector pos, Vector vel, int mass) {
        this.pos = pos;
        this.vel = vel;
        acc = new Vector(0, 0);
        this.mass=mass;
    }

    public void addForce(Vector force) {
        acc=acc.add(force.scale(1.0/mass));
    }

    public Vector forceTo(Planet p) {
        Vector to=p.pos.subtract(pos);
        return to.scale(mass*p.mass/to.length()/to.length()/to.length());
    }

    public void draw(Graphics2D g) {
        g.drawOval((int)pos.x, (int)pos.y, 20, 20);
    }

    public void update() {
        vel=vel.add(acc);
        pos=pos.add(vel);

        if(pos.x < 0 || pos.x > 800) vel.x*=-1;
        if(pos.y < 0 || pos.y > 800) vel.y*=-1;
    }
}