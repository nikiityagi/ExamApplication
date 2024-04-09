import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit{

  public Editor = ClassicEditor;

  qId:any;
  qTitle:any;
  question={
    quiz:{
      qId:'',
      qTitle:'',
    },
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:'',
  };

  constructor(private _route:ActivatedRoute,private _question:QuestionsService){}

  ngOnInit(): void {
    this.qId=this._route.snapshot.params['qid'];
    this.qTitle=this._route.snapshot.params['qTitle'];
    this.question.quiz['qId']=this.qId;
  }

  formSubmit()
  {
    if(this.question.content.trim()=='' || this.question.content==null)
    {
      return;
    }

    if(this.question.option1.trim()=='' || this.question.option1==null)
    {
      return;
    }

    if(this.question.option2.trim()=='' || this.question.option2==null)
    {
      return;
    }

    if(this.question.answer.trim()=='' || this.question.answer==null)
    {
      return;
    }

    //form submit
    this._question.addQuestions(this.question).subscribe((data:any)=>{
      Swal.fire('Success','Success in adding questions, Now Add Another One','success');
      this.question.content='';
      this.question.option1='';
      this.question.option2='';
      this.question.option3='';
      this.question.option4='';
      this.question.answer='';
    },
    (error)=>{
      Swal.fire('Error','Error in adding questions','error');
    })
  }

}
