import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import {RealizarTransacaoComponent} from "./realizar-transacao/realizar-transacao.component";
import { MovimentacaoDashboardComponent } from './movimentacao-dashboard/movimentacao-dashboard.component';


@NgModule({
  declarations: [
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppComponent
  ],
  providers: [],
  bootstrap: []
})
export class AppModule { }
