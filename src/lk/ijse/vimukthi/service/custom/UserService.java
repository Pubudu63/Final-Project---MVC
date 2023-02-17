package lk.ijse.vimukthi.service.custom;

import lk.ijse.vimukthi.dto.UserDTO;
import lk.ijse.vimukthi.service.SuperService;
import lk.ijse.vimukthi.service.exception.DuplicateException;
import lk.ijse.vimukthi.service.exception.InUseException;
import lk.ijse.vimukthi.to.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService extends SuperService {


   boolean addUser(UserDTO userDTO);

   boolean deleteUser(String id) throws InUseException, DuplicateException, SQLException, ClassNotFoundException;

   UserDTO searchUser(String id) throws SQLException, ClassNotFoundException;

   boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

   ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException;

   ArrayList<String> loadUserIDs() throws SQLException, ClassNotFoundException;

   boolean searchAdminEmail(UserDTO user) throws SQLException, ClassNotFoundException;
}
