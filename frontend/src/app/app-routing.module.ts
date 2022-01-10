import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './components/landing/landing.component';
import { VisualizationComponent } from './components/data-visualization/visualization/visualization.component';
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
  },
  {
    path: "visualize",
    component: VisualizationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
