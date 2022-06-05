import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  registerFormGroup!: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void { 
    this.registerFormGroup = this.formBuilder.group({
      user: this.formBuilder.group({
        username: new FormControl('',[Validators.required,Validators.minLength(5)]),
        password: new FormControl('',[Validators.required,Validators.minLength(5), Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')]),
        email: new FormControl('',[Validators.required,Validators.email])
      })
    })

  }

  onSubmit(): void {

    let user = this.registerFormGroup.controls['user'].value 

    this.authService.register(
      user.username,
      user.password,
      user.email
      ).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.router.navigate(['/'])
      },
      err => {
        this.errorMessage = "Nie udało się zarejestrowac"
        this.isSignUpFailed = true;
      }
    );

  }

  get username(){  return this.registerFormGroup.get('user.username') }
  get password(){  return this.registerFormGroup.get('user.password') }
  get email(){  return this.registerFormGroup.get('user.email') }

}