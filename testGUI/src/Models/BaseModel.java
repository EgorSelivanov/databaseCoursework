package Models;

public class BaseModel {
    protected long id;

    public BaseModel() {}

    public BaseModel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return this.id == baseModel.id;
    }
}
