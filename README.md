# 🍽️ WorkConnect

Sistema de gestión de suscripciones de comida que permite a los clientes recibir menús personalizados según sus preferencias, alergias y restricciones alimentarias.

## 📋 Descripción

WorkConnect es una plataforma integral que conecta a clientes con servicios de comida por suscripción. El sistema permite:

- Gestión completa de perfiles de clientes con preferencias alimentarias
- Suscripciones flexibles (semanales, mensuales)
- Generación automática de pedidos basados en menús personalizados
- Control de alérgenos y platos no deseados
- Seguimiento completo del ciclo de vida de pedidos
- Gestión de albaranes y entregas

## 🧭 Flujo General del Sistema

### 🧍 Cliente

1. El cliente se registra en la plataforma
2. Configura su perfil con:
   - Datos personales y dirección
   - Alergias
   - Platos no deseados
3. Contrata una suscripción (semanal o mensual)
4. El sistema genera automáticamente pedidos con menús
5. Seguimiento de pedidos: **Pendiente → Preparado → Enviado → Entregado**

### 🧑‍🍳 Empresa / Administrador

- Gestiona platos disponibles
- Crea y gestiona menús (combinaciones de platos)
- Asocia alérgenos a platos
- Supervisa pedidos, genera albaranes y registra entregas
- Consulta historial de clientes, menús y entregas

## 🗄️ Estructura de Base de Datos

### Tablas Principales

#### 🧍 Cliente
- `id`, `nombre`, `apellidos`, `email`, `teléfono`
- `dirección`, `ciudad`, `código_postal`
- `fecha_alta`, `activo`

#### 💳 Suscripción
- `id`, `cliente_id`, `nombre_plan`
- `frecuencia` (semanal, mensual)
- `precio`, `fecha_inicio`, `fecha_fin`
- `estado` (activa, pausada, cancelada)

#### 🧾 Pedido
- `id`, `suscripción_id`, `menu_id`
- `fecha_pedido`, `total`
- `estado` (pendiente, preparado, entregado, cancelado)

#### 📦 PedidoDetalle
- `id`, `pedido_id`, `plato_id`
- `cantidad`, `precio_unitario`

#### 📑 Albarán
- `id`, `pedido_id`, `número_albarán`
- `fecha_emisión`, `observaciones`

#### 🚚 Entrega
- `id`, `pedido_id`, `fecha_entrega`
- `estado` (pendiente, en_camino, entregado, incidencia)
- `observaciones`

#### 🍽️ Plato
- `id`, `nombre`, `descripción`, `calorías`
- `tipo` (entrante, principal, postre, bebida)
- `disponible`, `precio`

#### ⚠️ Alergeno
- `id`, `nombre`, `descripción`

#### 🍱 Menú
- `id`, `nombre`, `descripción`
- `tipo` (diario, semanal, personalizado)
- `fecha_inicio`, `fecha_fin`, `activo`

### Tablas de Relación

- **Plato_Alergeno**: Relaciona platos con alérgenos (N:M)
- **Plato_No_Deseado**: Platos que el cliente no desea (N:M)
- **Menu_Plato**: Platos que componen un menú (N:M)

## 🔗 Relaciones Entre Entidades

| Relación | Tipo | Descripción |
|----------|------|-------------|
| Cliente ↔ Suscripción | 1:N | Un cliente puede tener varias suscripciones |
| Suscripción ↔ Pedido | 1:N | Una suscripción genera varios pedidos |
| Pedido ↔ Albarán | 1:1 | Cada pedido tiene un albarán |
| Pedido ↔ Entrega | 1:1 | Cada pedido tiene una entrega asociada |
| Pedido ↔ Menú | N:1 | Un pedido puede estar basado en un menú |
| Menú ↔ Plato | N:M | Un menú contiene varios platos |
| Plato ↔ Alergeno | N:M | Un plato tiene varios alérgenos |
| Cliente ↔ Plato (no deseado) | N:M | Un cliente puede marcar varios platos como no deseados |
| Pedido ↔ PedidoDetalle | 1:N | Cada pedido tiene sus platos detallados |

## 🌐 API REST Endpoints

> ⚠️ **Nota**: Todos los endpoints están actualmente **En progreso** y se irán implementando gradualmente.

### 👤 Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clientes` | Listar clientes |
| GET | `/api/clientes/{id}` | Obtener cliente |
| POST | `/api/clientes` | Crear cliente |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Desactivar cliente |
| GET | `/api/clientes/{id}/suscripciones` | Listar suscripciones |
| GET | `/api/clientes/{id}/restricciones` | Ver restricciones |
| POST | `/api/clientes/{id}/platos-no-deseados` | Añadir plato no deseado |
| DELETE | `/api/clientes/{id}/platos-no-deseados/{platoId}` | Quitar plato no deseado |

### 💳 Suscripciones

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/suscripciones` | Listar suscripciones |
| GET | `/api/suscripciones/{id}` | Obtener una suscripción |
| POST | `/api/suscripciones` | Crear suscripción |
| PUT | `/api/suscripciones/{id}` | Actualizar suscripción |
| DELETE | `/api/suscripciones/{id}` | Cancelar suscripción |
| GET | `/api/suscripciones/{id}/pedidos` | Pedidos de la suscripción |

### 🧾 Pedidos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/pedidos` | Listar pedidos |
| GET | `/api/pedidos/{id}` | Obtener pedido |
| POST | `/api/pedidos` | Crear pedido |
| PUT | `/api/pedidos/{id}/estado` | Cambiar estado |
| GET | `/api/pedidos/{id}/detalle` | Ver detalle |
| POST | `/api/pedidos/{id}/detalle` | Añadir plato |
| DELETE | `/api/pedidos/{id}/detalle/{platoId}` | Eliminar plato |

### 📑 Albaranes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/albaranes` | Listar albaranes |
| GET | `/api/albaranes/{id}` | Ver albarán |
| POST | `/api/albaranes` | Crear albarán |
| GET | `/api/pedidos/{id}/albaran` | Ver albarán de pedido |

### 🚚 Entregas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/entregas` | Listar entregas |
| GET | `/api/entregas/{id}` | Ver entrega |
| POST | `/api/entregas` | Registrar entrega |
| PUT | `/api/entregas/{id}/estado` | Cambiar estado |

### 🍽️ Platos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/platos` | Listar platos |
| GET | `/api/platos/{id}` | Ver plato |
| POST | `/api/platos` | Crear plato |
| PUT | `/api/platos/{id}` | Actualizar plato |
| DELETE | `/api/platos/{id}` | Desactivar plato |
| GET | `/api/platos/{id}/alergenos` | Ver alérgenos |
| POST | `/api/platos/{id}/alergenos` | Asignar alérgeno |
| DELETE | `/api/platos/{id}/alergenos/{alergenoId}` | Quitar alérgeno |

### 🍱 Menús

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/menus` | Listar menús |
| GET | `/api/menus/{id}` | Ver menú |
| POST | `/api/menus` | Crear menú |
| PUT | `/api/menus/{id}` | Actualizar menú |
| DELETE | `/api/menus/{id}` | Eliminar menú |
| GET | `/api/menus/{id}/platos` | Ver platos del menú |
| POST | `/api/menus/{id}/platos` | Añadir platos al menú |
| DELETE | `/api/menus/{id}/platos/{platoId}` | Quitar plato del menú |

### ⚠️ Alérgenos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/alergenos` | Listar alérgenos |
| POST | `/api/alergenos` | Crear alérgeno |
| PUT | `/api/alergenos/{id}` | Actualizar |
| DELETE | `/api/alergenos/{id}` | Eliminar |

## 🚀 Instalación

\`\`\`bash
# Clonar el repositorio
git clone https://github.com/Makiita/workconnect.git

# Navegar al directorio
cd workconnect

# Instalar dependencias
npm install

# Configurar variables de entorno
cp .env.example .env

# Ejecutar migraciones de base de datos
npm run migrate

# Iniciar el servidor de desarrollo
npm run dev
\`\`\`

## 🛠️ Tecnologías

- Backend: [Especificar framework]
- Base de datos: [Especificar BD]
- Frontend: [Especificar framework]
- Autenticación: [Especificar método]

## 📝 Estado del Proyecto

🚧 **En desarrollo activo** - Los endpoints de la API están siendo implementados progresivamente.

## 👨‍💻 Autor

**Makiita**
- GitHub: [@Makiita](https://github.com/Makiita)

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue primero para discutir los cambios que te gustaría realizar.

---

⭐ Si este proyecto te resulta útil, considera darle una estrella en GitHub
