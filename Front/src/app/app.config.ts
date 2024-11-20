import {ApplicationConfig, InjectionToken} from "@angular/core";
import {provideRouter} from "@angular/router";
import {routes} from "./app.routes";
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {provideNgxMask} from "ngx-mask";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {AuthInterceptor} from "./auth/auth.interceptor";

export const API_BASE_URL = new InjectionToken<string>('API_BASE_URL');
const urlApi = 'http://localhost:8080/api/v1';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptorsFromDi()),
    provideNgxMask(),
    { provide: API_BASE_URL, useValue: urlApi },
    provideAnimationsAsync(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }, provideAnimationsAsync(), provideAnimationsAsync(), provideAnimationsAsync(), provideAnimationsAsync()
  ]
};
