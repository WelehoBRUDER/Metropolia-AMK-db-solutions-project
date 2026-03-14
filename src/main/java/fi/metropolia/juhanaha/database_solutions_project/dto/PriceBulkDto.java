package fi.metropolia.juhanaha.database_solutions_project.dto;

public class PriceBulkDto {
    private Integer cat;
    private double factor;

    public Integer getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
}
