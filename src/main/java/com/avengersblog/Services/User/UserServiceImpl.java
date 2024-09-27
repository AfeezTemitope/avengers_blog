package com.avengersblog.Services.User;

import com.avengersblog.Data.Model.Confirmation;
import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.ConfirmationRepository;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.UpdateUserProFilRequest;
import com.avengersblog.Dto.request.User.UserRequest;
import com.avengersblog.Dto.response.UpdateUserProFileResponse;
import com.avengersblog.Dto.response.User.UserResponse;
import com.avengersblog.Services.Email.EmailServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailServiceImpl emailService;



    @Override
    public UpdateUserProFileResponse updateUserProFile(UpdateUserProFilRequest updateUserProFilRequest) {
        userRepository.findUserByEmail(updateUserProFilRequest.getEmail());
        User user = new User();
        user.setFirstName(updateUserProFilRequest.getFirstName());
        user.setLastName(updateUserProFilRequest.getLastName());
        user.setEmail(updateUserProFilRequest.getEmail());
        user.setPassword(updateUserProFilRequest.getPassword());
        userRepository.save(user);

        UpdateUserProFileResponse updateUserProFileResponse = new UpdateUserProFileResponse();
        updateUserProFileResponse.setEmail(updateUserProFilRequest.getEmail());
        updateUserProFileResponse.setMessage("Successfully updated user profile");
        return updateUserProFileResponse;

    }

    @Override
    public UserResponse savedUser(UserRequest userRequest) {
        ValidationRegistration(userRequest);
        User user= new User();
//        user = userRepository.existsByEmail(userRequest.getEmail());
        if(userRepository.existsByEmail(user.getEmail())){ throw new RuntimeException("Email already exists");}
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        userRepository.save(user);
        UserResponse userResponse=new UserResponse();
        userResponse.setMessage("You have Register Successfully");
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        emailService.sendSimpleMailMessage(userRequest.getUserName(),userRequest.getEmail(),confirmation.getToken() );

        return userResponse;
    }


    @Override
    public Boolean verifyToken(String token) {
            Confirmation confirmation = confirmationRepository.findByToken(token);
            User user = userRepository.findUserByEmailIgnoreCase(confirmation.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return Boolean.TRUE;
        }


//    @Override
   private void ValidationRegistration(UserRequest userRequest){
        if(userRequest.getUserName().trim().isEmpty() || userRequest.getFirstName().trim().isEmpty() || userRequest.getLastName().trim().isEmpty() || userRequest.getPassword().trim().isEmpty()){
            throw new RuntimeException("User cannot be empty");
        }

     }
    }


