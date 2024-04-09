import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-loginup',
  templateUrl: './loginup.component.html',
  styleUrls: ['./loginup.component.css']
})
export class LoginupComponent {

  loginData={
     username:'',
     password:'',
  };

  constructor(private snack:MatSnackBar,private login:LoginService){}

  ngOnInit() : void {}

  formSubmit()
  {
    console.log('login btn clicked');

    if(this.loginData.username.trim()=='' || this.loginData.username==null)
    {
      this.snack.open('Username is required!!','ok',{
        duration:2000,
      });
      return;
    }

    if(this.loginData.password.trim()=='' || this.loginData.password==null)
    {
      this.snack.open('Passwordu is required!!','ok',{
        duration:2000,
      });
      return;
    }

    //request to server to generate token

    this.login.generateToken(this.loginData).subscribe(
      (data:any)=>{
        console.log('success');
        console.log(data);

        //login...
        this.login.loginUser(data.token);
        this.login.getCurrentUser().subscribe(
          (user:any)=>{
            this.login.setUser(user);
            console.log(user);
            //redirect .....ADMIN: admin-dashboard
            //redirect .....NORMAL: normal-dashboard
            if(this.login.getUserRole()=='ADMIN')
            {
                window.location.href='/admin';
                this.login.loginStatusSubject.next(true);
            }
            else if(this.login.getUserRole()=='NORMAL')
            {
               window.location.href='/user';
               this.login.loginStatusSubject.next(true);
            }
            else
            {
               this.login.logout();
            }
          }
        )
      },
      (error: any)=>{
        console.log("Error!");
        console.log(error);
        this.snack.open('Invalid Details!!','Try Again',{
          duration:2000,
        });
              }
    );
  }
}
