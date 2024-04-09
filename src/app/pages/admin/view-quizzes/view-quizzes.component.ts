import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit{
[x: string]: any;

  quizzes=[
    {
      qId:23,
      title:'Basic Java Quiz',
      description:'this is java quiz',
      maxMarks:'100',
      numberOfQuestions:'10',
      active:'',
      category:{
        title:'Programming'
      },
    },
    {
      qId:23,
      title:'Basic Java Quiz',
      description:'this is java quiz',
      maxMarks:'100',
      numberOfQuestions:'10',
      active:'',
      category:{
        title:'Programming'
      },
    },
  ];

  constructor(private _quiz:QuizService){}

  ngOnInit(): void{
     this._quiz.quizzes().subscribe(
      (data:any)=>
      {
        this.quizzes=data;
        console.log(this.quizzes);
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!','Error in loading data','error');
      });
  }

  //delete quiz
  deleteQuiz(qId:any)
  {
    Swal.fire({
      icon:'info',
      title:'Are you sure want to delete this Quiz??',
      confirmButtonText:'Delete',
      showCancelButton:true,
    }).then((result)=>{
      if(result.isConfirmed)
      {
        //delete
        this._quiz.deleteQuiz(qId).subscribe(
          (data)=>{
            this.quizzes=this.quizzes.filter((quiz)=>quiz.qId!=qId)
            Swal.fire('Success','Quiz deleted','success');
          },
          (error)=>{
            Swal.fire('Error','Error in Quiz deleted','error');
          }
        );
      }
    });
  }


}
