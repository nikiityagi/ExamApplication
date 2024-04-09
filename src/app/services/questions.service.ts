import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  constructor(private _http:HttpClient) { }

  //get question
  public getQuestionsOfQuiz(qid:any)
  {
    return this._http.get(`${baseUrl}/question/quiz/all/${qid}`);
  }

  public getQuestionsOfQuizForTest(qid:any)
  {
    return this._http.get(`${baseUrl}/question/quiz/${qid}`);
  }

  //add questions
  public addQuestions(question:any)
  {
    return this._http.post(`${baseUrl}/question/`,question);
  }

  //delete Questions
  public deleteQuestion(quesId:any)
  {
    return this._http.delete(`${baseUrl}/question/${quesId}`);
  }

  //update Questions
  public updateQuestion(question:any)
  {
    return this._http.put(`${baseUrl}/question/update`,question);
  }

  //eval quiz
  public evalQuiz(question:any)
  {
    return this._http.post(`${baseUrl}/question/eval-quiz`,question);
  }
}
