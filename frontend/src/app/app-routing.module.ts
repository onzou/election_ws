import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './components/landing/landing.component';
import { VotingComponent } from './components/voting/voting.component';

const routes: Routes =
[
  {
    path: "",
    component: LandingComponent
  },
  {
    path: "vote",
    component: VotingComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
