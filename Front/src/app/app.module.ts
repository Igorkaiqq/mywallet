import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {HighchartsChartModule} from "highcharts-angular";
import {ChartModule} from "primeng/chart";
import {LOCALE_CONFIG, NgxDaterangepickerMd} from "ngx-daterangepicker-material";

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    HighchartsChartModule,
    ChartModule,
    NgxDaterangepickerMd.forRoot({
      format: 'DD/MM/YYYY',
      firstDay: 1,
      applyLabel: 'OK',
      customRangeLabel: 'Customizado'
    })
  ],
  providers: [
    { provide: LOCALE_CONFIG, useValue: { format: 'DD/MM/YYYY', firstDay: 1 } }
  ],
  bootstrap: []
})
export class AppModule { }
