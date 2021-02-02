# RestCountries

This github repository contains an android app files that displays information about
countries in Asia  by consuming a rest api.

- It display the following data - name, capital, flag, region, subregion, population, borders &
languages.
- Used Rest API docs - https://restcountries.eu/ 

## Workflow of the app

1. When the user clicks fetch button, the app fetches the json data from https://restcountries.eu/rest/v2/region/asia/.
2. This json data is then manipulated to retrieve required information.
3. The list view then displays all the countries name in Asia.
4. On clicking the list view item, a toast pops up that displays all the required information about the selected country.

<sup> Created by **Siddharth Dubey** </sup>
