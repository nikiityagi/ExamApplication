import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css']
})
export class ViewQuestionsComponent implements OnInit {

  qId: any;
  qTitle:any;
  questions=[
    {
      "quesId":'',
      "answer":"Object Oriented Programming System",
      "content":"What is the Defination of OOPS?",
      "image":"java.png",
      "option1":"Object Oriented Programming System",
      "option2":"Object Oriented Programming Set",
      "option3":"Object Origin Programm System",
      "option4":"Oval Oil Provide Set",
      "quiz":
      {
        "qId":202
      }
  }
  ];

  constructor(private _route:ActivatedRoute,
              private _questions:QuestionsService,
              private _snack:MatSnackBar){}
  
  ngOnInit(): void {

    this.qId=this._route.snapshot.params['qid'];
    this.qTitle=this._route.snapshot.params['title'];
    this._questions.getQuestionsOfQuiz(this.qId).subscribe((data:any)=>{
      console.log(data);
      this.questions=data;
    },
    (error)=>{
      console.log(error);
    }
    )
   
  }

  //delete question
  deleteQuestion(qid:any)
  {
    Swal.fire({
      icon:'info',
      showCancelButton:true,
      confirmButtonText:'Delete',
      title:'Are you sure??'
    }).then((result)=>{
      if(result.isConfirmed)
      {
        //confirm
        this._questions.deleteQuestion(qid).subscribe(
          (data:any)=>{
            this._snack.open('Question Deleted','',{
              duration:3000,
            });
            this.questions=this.questions.filter((q)=>q.quesId!=qid)
          },
          (error)=>{
            this._snack.open('Error in deleting questions','',{
              duration:3000,
            });
            console.log(error);
          }
          );
      }
    });
  }

}
