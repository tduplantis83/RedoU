import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { RegisterComponent } from './components/register/register.component';
import { PostsComponent } from './components/posts/posts.component';
import { ArraySortPipe } from './Pipes/array-sort.pipe';
import { MeasurementConverterPipe } from './Pipes/measurement-converter.pipe';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserProfileComponent,
    PageNotFoundComponent,
    NavBarComponent,
    SearchResultsComponent,
    RegisterComponent,
    PostsComponent,
    ArraySortPipe,
    MeasurementConverterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthService, ArraySortPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
