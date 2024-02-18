import streamlit as st
import pandas as pd
import plotly.express as px
import statsmodels.api as sm
import numpy as np

# Załadowanie danych
@st.cache_data
def load_data(file):
    data = pd.read_csv(file)
    if 'id' in data.columns:
        data = data.drop(columns=['id'])
    return data

data = load_data('cleaned_messy_data.csv')

# Określenie kolumn numerycznych i kategorycznych
numeric_columns = data.select_dtypes(include=[np.float64, np.int64]).columns.tolist()
categorical_columns = data.select_dtypes(include=['object']).columns.tolist()

st.write("""
# Dashboard analizy danych

Ten dashboard umożliwia interaktywną analizę danych i budowanie modelu regresji. Możesz wybrać różne typy wizualizacji, zmienne do analizy oraz różne wartości dla zmiennych kategorycznych.
""")

# Wybór rodzaju wizualizacji
st.sidebar.header('Wybierz rodzaj wizualizacji:')
visualization_type = st.sidebar.selectbox('Wybierz typ wizualizacji:', ['Interaktywny wykres i model regresji', 'Heatmapa korelacji', 'Histogramy i Wykresy punktowe dla zmiennych numerycznych', 'Histogramy i Wykresy pudełkowe dla zmiennych kategorycznych'])

# Zmienna zależna
st.sidebar.header('Wybierz zmienną zależną:')
default_y_var = data.columns.tolist().index('price')
y_var = st.sidebar.selectbox('Wybierz zmienną na osi Y:', options=data.columns, index = default_y_var)

# Logika wizualizacji
if visualization_type == 'Interaktywny wykres i model regresji':
    st.header('Interaktywny wykres i model regresji')
    st.markdown("""
    ### Interaktywny wykres
    Ta sekcja umożliwia tworzenie interaktywnych wykresów, które pomagają wizualizować zależności między wybraną zmienną zależną a jedną lub więcej zmiennymi niezależnymi. Możesz wybrać różne zmienne i zobaczyć, jak zmieniają się względem siebie.
    """)
    # Interaktywne wykresy - wybór wielu zmiennych niezależnych
    st.sidebar.header('Wybierz zmienne dla interaktywnych wykresów:')
    interactive_x_vars = st.sidebar.multiselect('Wybierz zmienne dla osi x:', options=[col for col in data.columns if col != y_var and data[col].dtype in [np.float64, np.int64]], default=[col for col in data.columns if col != y_var and data[col].dtype in [np.float64, np.int64]])

    if interactive_x_vars:
        fig = px.scatter(data, x=interactive_x_vars, y=y_var, title=f'Interaktywny wykres dla {y_var}')
        st.plotly_chart(fig, use_container_width=True)

elif visualization_type == 'Heatmapa korelacji':
    st.header('Heatmapa korelacji')
    st.markdown("""
    Heatmapa korelacji przedstawia współczynniki korelacji Pearsona między zmiennymi numerycznymi w zbiorze danych. Wartości bliskie 1 lub -1 wskazują na silną korelację dodatnią lub ujemną.
    """)
    numeric_data = data.select_dtypes(include=[np.float64, np.int64])
    correlation_matrix = numeric_data.corr()
    fig = px.imshow(correlation_matrix, text_auto=True, aspect='auto', color_continuous_scale='RdBu_r')
    fig.update_layout(title='Heatmapa korelacji między zmiennymi')
    st.plotly_chart(fig, use_container_width=True)

elif visualization_type == 'Histogramy i Wykresy punktowe dla zmiennych numerycznych':
    st.header('Histogramy i Wykresy punktowe dla zmiennych numerycznych')
    st.markdown("""
    Ta sekcja pozwala na generowanie histogramów dla wybranych zmiennych numerycznych, aby zobaczyć ich rozkład, oraz wykres punktowy, aby zobaczyć zależności między zmienną zależną a wybranymi zmiennymi numerycznymi.
    """)
    st.sidebar.header('Wybierz zmienną numeryczną dla Histogramy i Wykresy punktowe:')
    selected_numeric_var = st.sidebar.selectbox('Wybierz zmienną numeryczną:', options=[col for col in numeric_columns if col!= y_var])
    if selected_numeric_var:
        # Histogram
        fig_hist = px.histogram(data, x=selected_numeric_var, title=f'Histogram dla {selected_numeric_var}')
        st.plotly_chart(fig_hist, use_container_width=True)

        # Wykres punktowy
        if selected_numeric_var != y_var:
            fig_scatter = px.scatter(data, x=selected_numeric_var, y=y_var, title=f'Zależność {y_var} od {selected_numeric_var}', trendline="ols")
            st.plotly_chart(fig_scatter, use_container_width=True)

elif visualization_type == 'Histogramy i Wykresy pudełkowe dla zmiennych kategorycznych':
    st.header('Histogramy i Wykresy pudełkowe dla zmiennych kategorycznych')
    st.markdown("""
    W tej sekcji możesz wygenerować histogramy dla zmiennych kategorycznych, aby zobaczyć rozkład kategorii, oraz wykres pudełkowy, aby zbadać zależności między zmiennymi kategorycznymi a zmienną zależną.
    """)
    st.sidebar.header('Wybierz zmienną kategoryczną dla Histogramy i Wykresy pudełkowe:')
    selected_categorical_var = st.sidebar.selectbox('Wybierz zmienną kategoryczną:', options=categorical_columns)
    st.sidebar.markdown("### Filtry danych")
    st.sidebar.write("Użyj poniższych filtrów, aby dostosować dane wyświetlane na wykresach i analizach.")

    if 'cut' in data.columns:
        selected_cuts = st.sidebar.multiselect("Wybierz typy szlifu:", options=data['cut'].unique(), default=data['cut'].unique())
        data = data[data['cut'].isin(selected_cuts)]

    if 'color' in data.columns:
        selected_colors = st.sidebar.multiselect("Wybierz kolory:", options=data['color'].unique(), default=data['color'].unique())
        data = data[data['color'].isin(selected_colors)]

    if 'clarity' in data.columns:
        selected_clarities = st.sidebar.multiselect("Wybierz poziomy przejrzystości:", options=data['clarity'].unique(), default=data['clarity'].unique())
        data = data[data['clarity'].isin(selected_clarities)]

    if selected_categorical_var:
        # Histogram
        fig_cat_hist = px.histogram(data, x=selected_categorical_var, title=f'Rozkład zmiennej kategorycznej {selected_categorical_var}')
        st.plotly_chart(fig_cat_hist, use_container_width=True)

        # Wykres pudełkowy
        fig_box = px.box(data, x=selected_categorical_var, y=y_var, title=f'Zależność {y_var} od kategorii {selected_categorical_var}')
        st.plotly_chart(fig_box, use_container_width=True)


# Tabela z danymi
st.header('Próbka danych:')
st.write(data.sample(10))

# Model regresji
if visualization_type == 'Interaktywny wykres i model regresji':
    st.header('Model regresji i analiza reszt')
    st.markdown("""
    ### Opis modelu regresji
    W tej sekcji budujemy model regresji liniowej z wybranymi zmiennymi niezależnymi. Poniżej przedstawione są podsumowanie modelu, wykres rzeczywistych vs. przewidywanych wartości oraz wykres reszt.
    
    **Kluczowe parametry modelu:**
    - **R-squared (R²):** Współczynnik determinacji, mierzy jakość dopasowania modelu. Wartość bliska 1 wskazuje na silne dopasowanie.
    - **Adj. R-squared:** Skorygowany współczynnik determinacji, uwzględnia liczbę predyktorów w modelu. Im bliżej R², tym lepiej.
    - **coef:** Współczynniki dla zmiennych niezależnych, pokazują ich wpływ na zmienną zależną.
    - **P>|t|:** P-wartość dla każdego współczynnika, wartość poniżej 0.05 sugeruje, że zmienna jest istotna statystycznie.
    """)
    
    # Zmienne niezależne dla modelu regresji i analizy reszt
    st.sidebar.header('Wybierz zmienne niezależne dla modelu regresji i analizy reszt:')
    regression_x_vars = st.sidebar.multiselect('Wybierz zmienne na osi X:', options=[col for col in data.columns if col != y_var and data[col].dtype in [np.float64, np.int64]], default=[col for col in data.columns if col != y_var and data[col].dtype in [np.float64, np.int64]])
    
    x = data[regression_x_vars].select_dtypes(include=[np.float64, np.int64])
    y = data[y_var] 

    if data[y_var].dtype not in [np.float64, np.int64]:
        st.error("Wybrano zmienną kategoryczną jako zmienną zależną. Proszę wybrać zmienną numeryczną.")
        st.stop()


    # Funkcja eliminacji wstecznej
    def backward_elimination(data, target, significance_level=0.05):
        features = data.columns.tolist()
        while len(features) > 0:
            features_with_constant = sm.add_constant(data[features])
            p_values = sm.OLS(target, features_with_constant).fit().pvalues[1:]
            max_p_value = p_values.max()
            if max_p_value >= significance_level:
                excluded_feature = p_values.idxmax()
                features.remove(excluded_feature)
                st.write(f"Usunięto: {excluded_feature}, P-wartość: {max_p_value}")
            else:
                break
        return features

    significant_features = backward_elimination(x, y)

    st.write(f"Znaczące zmienne: {significant_features}")

    X_significant = sm.add_constant(data[significant_features])
    model = sm.OLS(y, X_significant).fit()

    st.write(model.summary())

    # Przewidywane wartości
    predicted_values = model.predict(X_significant)

    # Reszty
    residuals = y - predicted_values

    # Wykres rzeczywistych vs przewidywanych wartości
    fig_rvp = px.scatter(x=y, y=predicted_values, labels={'x': 'Rzeczywiste wartości', 'y': 'Przewidywane wartości'}, title='Rzeczywiste vs. Przewidywane wartości')
    fig_rvp.add_shape(type='line', x0=y.min(), y0=y.min(), x1=y.max(), y1=y.max(), line=dict(color='Red', dash='dash'))
    st.plotly_chart(fig_rvp, use_container_width=True)

    # Wykres reszt
    fig_resid = px.scatter(x=predicted_values, y=residuals, labels={'x': 'Przewidywane wartości', 'y': 'Reszty'}, title='Reszty vs. Przewidywane wartości')
    fig_resid.add_hline(y=0, line_dash="dash", line_color="red")
    st.plotly_chart(fig_resid, use_container_width=True)

