import {Inject, Injectable} from "@angular/core";
import {API_BASE_URL} from "../../app.config";
import {HttpClient} from "@angular/common/http";
import {Meta} from "@angular/platform-browser";
import {Observable} from "rxjs";
import {MetasFinanceiras} from "../../models/metas/metas";

@Injectable({
  providedIn: 'root'
})

export class MetasService {
  private apiBaseUrl = API_BASE_URL;

  constructor(private http: HttpClient, @Inject(API_BASE_URL) private apiUrl: string) {

  }

  registrarMeta(meta: MetasFinanceiras): Observable<any> {

    console.log(meta);
    return this.http.post<Meta>(`${this.apiUrl}/metas-usuario`, meta);

  }

  getMetas(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/metas-usuario/categoria/${id}`);
  }
}
