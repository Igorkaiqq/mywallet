import { ApplicationConfig } from "@angular/core";
import { provideRouter } from "@angular/router";
import { routes } from "./app.routes";
import { provideHttpClient } from "@angular/common/http";
import {NgxMaskDirective, NgxMaskPipe, provideNgxMask} from "ngx-mask";
import { ENVIRONMENT_INITIALIZER, InjectionToken} from "@angular/core";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

export const API_BASE_URL = new InjectionToken<string>('API_BASE_URL');
const urlApi = 'http://localhost:8080/api/v1';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    provideNgxMask(),
    { provide: API_BASE_URL, useValue: urlApi }, provideAnimationsAsync(),
  ],
};
