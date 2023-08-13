package com.Cranco.Cranco.controller;

import com.Cranco.Cranco.dto.ResponseDTO;
import com.Cranco.Cranco.dto.UserDTO;
import com.Cranco.Cranco.service.UserService;
import com.Cranco.Cranco.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

//    @Autowired
//    private  UserService userService;
//
//    @Autowired
//    private ResponseDTO responseDTO;

    private final UserService userService;
    private final ResponseDTO responseDTO;

    @Autowired 
    public UserController(UserService userService, ResponseDTO responseDTO) {
        this.userService = userService;
        this.responseDTO = responseDTO;
    }


    @PostMapping(value="/saveUser")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDTO userDTO){
        try{
            //return ResponseEntity.ok().body("Success");
            String res= userService.SaveUser(userDTO);
            if(res.equals("00")) {
                responseDTO.setCode(VarList.Respond_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDTO.setCode(VarList.Respond_DUPLICATED);
                responseDTO.setMessage("User already exist");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.Respond_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.Respond_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/updateUser")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO){
        try{
            //return ResponseEntity.ok().body("Success");
            String res = userService.updateUser(userDTO);
            if(res.equals("00")) {
                responseDTO.setCode(VarList.Respond_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(VarList.Respond_NO_DATA_FOUND);
                responseDTO.setMessage("Not a registered user");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.Respond_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.Respond_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseDTO> getAllUsers(){
        try{
            List<UserDTO> userDTOList = userService.getAllUsers();
            responseDTO.setCode(VarList.Respond_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(userDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        }catch(Exception ex){
            responseDTO.setCode(VarList.Respond_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchUser/{userId}")
    public ResponseEntity<ResponseDTO> searchUser(@PathVariable int userId){
        try{
            UserDTO userDTO = userService.searchUser(userId);
            if(userDTO != null) {
                responseDTO.setCode(VarList.Respond_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.Respond_NO_DATA_FOUND);
                responseDTO.setMessage("No user available");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.Respond_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable int userId){
        try{
            String res = userService.deleteUser(userId);
            if(res.equals("00")) {
                responseDTO.setCode(VarList.Respond_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.Respond_NO_DATA_FOUND);
                responseDTO.setMessage("No user available");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.Respond_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
