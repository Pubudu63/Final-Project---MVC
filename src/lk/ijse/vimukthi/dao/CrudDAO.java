package lk.ijse.vimukthi.dao;


import lk.ijse.vimukthi.entity.SuperEntity;

import java.io.Serializable;

public interface CrudDAO<T extends SuperEntity, ID extends Serializable> extends SuperDAO {
}
