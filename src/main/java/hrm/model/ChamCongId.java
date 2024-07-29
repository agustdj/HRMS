package hrm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChamCongId implements Serializable {
    @Column(name = "ID")
    private int id;
    @Column(name = "IDNV", length = 10)
    private String idNV;
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChamCongId that = (ChamCongId) o;
        return id == that.id && Objects.equals(idNV, that.idNV);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idNV);
    }*/
}