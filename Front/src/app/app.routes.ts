import { RouterModule, Routes } from "@angular/router";
import { CadastroUsuarioComponent } from "./cadastro-usuario/cadastro-usuario.component";
import { LoginComponent } from "./login/login.component";
import { WalletAppComponent } from "./wallet-app/wallet-app.component";
import {RealizarTransacaoComponent} from "./realizar-transacao/realizar-transacao.component";
// import { HttpClienteModule } from '@angular/common/http';

export const routes: Routes = [
    {
        path: 'cadastrar-usuario',
        component: CadastroUsuarioComponent
    },
    {
        path: 'wallet',
        component: WalletAppComponent
    },
    {
        path: 'realizar-transacao',
        component: RealizarTransacaoComponent
    },
    {
        path: '**',
        component: LoginComponent
    }
];

export const AppRoutingModule = RouterModule.forRoot(routes);
