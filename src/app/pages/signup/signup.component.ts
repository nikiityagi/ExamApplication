import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
   constructor(private userService:UserService,private snack:MatSnackBar){}

   public user={
    username:'',
    password:'',
    firstname:'',
    lastname:'',
    email:'',
    phone:'',
    profile:''
   };

   ngOnInit():void{}

   formSubmit(){
    console.log(this.user);
    if(this.user.username=='' || this.user.username==null)
    {
      this.snack.open('Username is required!!','ok',{
        duration:2000,
      });
      return;
    }

    //add user:userservices
       this.userService.addUser(this.user).subscribe(
        (data)=>{
          //success
          console.log(data);
         // alert('success');
         Swal.fire('Success','User is registered','success');
        },
        (error)=>{
          //error
          console.log(error);
        //  alert('something went wrong');
        this.snack.open('Something went wrong!!','ok',{
          duration:2000,
        });
        }
       )
     }

     //this.user

}
