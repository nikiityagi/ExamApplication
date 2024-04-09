import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-quiz-start',
  templateUrl: './quiz-start.component.html',
  styleUrls: ['./quiz-start.component.css']
})
export class QuizStartComponent implements OnInit{

  qid:any;
  questions:any;

  marksGot=0;
  correctAnswer=0;
  attempted=0;

  isSubmit=false;

  timer:any;

  constructor(private locationSt:LocationStrategy,
              private _route:ActivatedRoute,
              private _question:QuestionsService){}

  ngOnInit(): void {
    this.preventBackButton();
    this.qid=this._route.snapshot.params['qid'];
    console.log(this.qid);
    this.loadQuestions();
  }

  loadQuestions()
  {
    this._question.getQuestionsOfQuizForTest(this.qid).subscribe(
      (data:any)=>
        {
          this.questions=data;
           
          this.timer=this.questions.length*2*60;


          // this.questions.forEach((q:any)=>{
          //     q['givenAnswer']='';
          // });

          console.log(this.questions);
          this.startTimer();
        },
        (error)=>{
          console.log(error);
          Swal.fire("Error","Error in loading questions of quiz",'error');
        });
  }

  preventBackButton(){
    history.pushState(null,'',location.href);
    this.locationSt.onPopState(()=>{
      history.pushState(null,'',location.href);
    });
  }

  submitQuiz()
  {
    Swal.fire({
      title:'Do you want to submit the quiz?',
      showCancelButton:true,
      confirmButtonText:'Submit',
      icon:'info',
    }).then((e)=>{
      if(e.isConfirmed){
          //calculation
          this.evalQuiz();
      }
    });
  }

  startTimer()
  {
    let t:any=window.setInterval(()=>{
      //code
      if(this.timer<=0)
        {
          this.evalQuiz();
          clearInterval(t);
        }
        else{
          this.timer--;
        }
    },1000);
  }

  getFormattedTime()
  {
    let mm=Math.floor(this.timer/60);
    let ss=this.timer-mm*60;
    return `${mm} min: ${ss} sec`;
  }

  evalQuiz()
  {
    this._question.evalQuiz(this.questions).subscribe(
      (data:any)=>{
        console.log(data);
        this.marksGot=parseFloat(Number(data.marksGot).toFixed(2));
        this.correctAnswer=data.correctAnswer;
        this.attempted=data.attempted;
        this.isSubmit=true;
      },
      (error)=>{
        console.log(error);
      }
    )
    // this.isSubmit=true;
          
    // this.questions.forEach((q:any)=>{
    //   if(q.givenAnswer==q.answer)
    //     {
    //       this.correctAnswers++;
    //       let marksSingle=
    //       this.questions[0].quiz.maxMarks/this.questions.length;
    //       this.marksGot+=marksSingle;
    //     }

    //     if(q.givenAnswer.trim()!='')
    //       {
    //         this.attempted++;
    //       }
    // });
    // console.log("Correct Answers:"+this.correctAnswers);
    // console.log("Marks Got"+this.marksGot);
    // console.log('attempted'+this.attempted);
    // console.log(this.questions);
  }

  printPage()
  {
    window.print();
  }

}
