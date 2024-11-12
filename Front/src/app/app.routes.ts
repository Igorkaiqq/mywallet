import { RouterModule, Routes } from "@angular/router";
import { CadastroUsuarioComponent } from "./pages/cadastro-usuario/cadastro-usuario.component";
import { LoginComponent } from "./pages/login/login.component";
import { WalletAppComponent } from "./pages/wallet-app/wallet-app.component";
import {RealizarTransacaoComponent} from "./pages/realizar-transacao/realizar-transacao.component";
import {MovimentacaoDashboardComponent} from "./pages/movimentacao-dashboard/movimentacao-dashboard.component";
import {AuthGuard} from "./service/authGuard/auth-guard.service";
import {
  CadastroContaBancariaComponent
} from "./pages/conta-bancaria/cadastro-conta-bancaria/cadastro-conta-bancaria.component";
import {CategoriasComponent} from "./pages/categorias/categorias.component";


export const routes: Routes = [
    {
        path: 'cadastrar-usuario',
        component: CadastroUsuarioComponent
    },
    {
        path: 'tela-inicial',
        component: MovimentacaoDashboardComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'wallet',
        component: WalletAppComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'realizar-transacao',
        component: RealizarTransacaoComponent,
        canActivate: [AuthGuard]
    },
    {
      path: 'cadastrar-conta-bancaria',
      component: CadastroContaBancariaComponent,
      canActivate: [AuthGuard]
    },
    {
      path: 'categorias',
      component: CategoriasComponent,
      canActivate: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
];

export const AppRoutingModule = RouterModule.forRoot(routes);
