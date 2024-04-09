import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent {

  qid:any;
  quiz:any;

  constructor(private _route:ActivatedRoute,
              private _quiz:QuizService,
              private _router:Router){}

  ngOnInit():void{
    this.qid=this._route.snapshot.params['qid'];

    this._quiz.getQuiz(this.qid).subscribe(
      (data:any)=>{
        console.log(data);
        this.quiz=data;
      },
      (error)=>{
        console.log(error);
        alert("Error in quiz loading");
      }
    )
  }

  startQuiz()
  {
    Swal.fire({
      title:'Do you want to start the quiz??',
      showCancelButton:true,
      confirmButtonText:'Start',
      denyButtonText:'Do not Save',
      icon:'info',
    }).then((result)=>{
      if(result.isConfirmed)
        {
           this._router.navigate(['/start-quiz/'+this.qid]);
        }
        else if(result.isDenied)
          {
            Swal.fire('Changes are not saved','','info');
          }
    });
  }

}
