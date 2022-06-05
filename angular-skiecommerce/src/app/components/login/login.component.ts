import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  loginFormGroup!: FormGroup;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  constructor(
     private authService: AuthService,
     private tokenStorage: TokenStorageService,
     private formBuilder: FormBuilder,
     private router: Router) { }

  ngOnInit(): void {

    this.loginFormGroup = this.formBuilder.group({
      user: this.formBuilder.group({
        username: new FormControl('',[Validators.required,Validators.minLength(3)]),
        password: new FormControl('',[Validators.required,Validators.minLength(3)])
      })
    })
    
    
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.router.navigate(['/'])
    }

  }

  onSubmit(): void {

    let user = this.loginFormGroup.controls['user'].value 

    this.authService.login(
      user.username,
      user.password
    ).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        console.log(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        window.location.reload();
      },
      err => {
        this.errorMessage = "Nie udało się zalogować"
        this.isLoginFailed = true;
      }
    );

  }
  
  reloadPage(): void {
    window.location.reload();
  }

  get username(){  return this.loginFormGroup.get('user.username') }
  get password(){  return this.loginFormGroup.get('user.password') }

}