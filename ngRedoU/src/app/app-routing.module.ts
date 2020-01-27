import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { HomeComponent } from './components/home/home.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent, runGuardsAndResolvers: 'always' },
  { path: 'login', component: LoginComponent, runGuardsAndResolvers: 'always' },
  { path: 'register', component: RegisterComponent, runGuardsAndResolvers: 'always' },
  { path: 'users', component: UserProfileComponent, runGuardsAndResolvers: 'always' },
  { path: 'search', component: SearchResultsComponent, runGuardsAndResolvers: 'always'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes,
      {useHash: true, onSameUrlNavigation: 'reload'})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
