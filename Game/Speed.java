public class Speed {
    private double v[], a[], default_a[], max_v[];
    public Speed(double default_ax, double default_ay, double max_vx, double max_vy) {
        this.v = new double[]{0, 0};
        this.a = new double[]{0, 0};
        this.default_a = new double[]{default_ax, default_ay};
        this.max_v = new double[]{max_vx, max_vy};
    }



    // セット関数群ここから
    public void set_v(double new_vx, double new_vy) {
        this.v[0] = new_vx;
        this.v[1] = new_vy;
        if (this.v[0] > this.max_v[0]) {
            this.v[0] = this.max_v[0];
        } else if (this.v[0] < -this.max_v[0]) {
            this.v[0] = -this.max_v[0];
        }
        if (this.v[1] > this.max_v[1]) {
            this.v[1] = this.max_v[1];
        } else if (this.v[1] < -this.max_v[1]) {
            this.v[1] = -this.max_v[1];
        }
    }
    public void set_a(double new_ax, double new_ay) {
        this.a[0] = new_ax;
        this.a[1] = new_ay;
    }
    public void set_default_a(double new_default_ax, double new_default_ay) {
        this.default_a[0] = new_default_ax;
        this.default_a[1] = new_default_ay;
    }
    public void set_max_v(double new_max_vx, double new_max_vy) {
        this.max_v[0] = new_max_vx;
        this.max_v[1] = new_max_vy;
    }
    // セット関数群ここまで



    // 取得関数群ここから
    public double get_vx() {
        return this.v[0];
    }
    public double get_vy() {
        return this.v[1];
    }
    public double get_ax() {
        return this.a[0];
    }
    public double get_ay() {
        return this.a[1];
    }
    public double get_default_ax() {
        return this.default_a[0];
    }
    public double get_default_ay() {
        return this.default_a[1];
    }
    // 取得関数群ここまで



    // 加減速関数群ここから
    public void accelerate_x() {
        set_a(this.get_default_ax(), this.get_ay());
    }
    public void decelerate_x() {
        set_a(-this.get_default_ax(), this.get_ay());
    }
    public void accelerate_y() {
        set_a(this.get_ax(), this.get_default_ay());
    }
    public void decelerate_y() {
        set_a(this.get_ax(), -this.get_default_ay());
    }
    // 加減速関数群ここまで
}
