;(() => {
  let heroes = []
  let selectedHero

  function showForm(isShow) {
    const formElem = document.getElementById('form')
    if (isShow) formElem.classList = 'visible'
    else formElem.classList = ''
  }

  function selectHero(hero) {
    const formElem = document.getElementById('form')
    /*
     * MISSION III: Pre-filling the form
     * 1. Pre-fill the form with the hero's name and the hero's superpower
     * 2. Call the "showForm" function to display the form
     * 3. Set the "selectedHero" variable, which has been declared at the beginning of the file,
     *    to be equal to the "hero" object. The "selectedHero" will be used in the other missions
     */

    // 1. Pre-fill the form with the hero's name and the hero's superpower
    formElem.name.value = hero.name
    formElem.superpower.value = hero.superpower

    showForm(true)

    selectedHero = hero
  }

  function createHeroElement(hero) {
    const imgElem = document.createElement('img')
    imgElem.setAttribute('src', hero.imageURL)

    const titleElem = document.createElement('h3')
    titleElem.innerHTML = hero.name

    const heroElem = document.createElement('div')
    heroElem.setAttribute('class', 'hero')
    heroElem.appendChild(imgElem)
    heroElem.appendChild(titleElem)

    heroElem.addEventListener('click', () => selectHero(hero))

    return heroElem
  }

  function displayHeroes(heroes) {
    const heroesElem = document.getElementById('heroes')
    /*
     * MISSION II: Displaying heroes
     * 1. Loop through the "heroes" array
     * 2. In each iteration:
     *   2.1 Create a new hero element by calling the "createHeroElement" function.
     *   2.2 Append the created hero element to be a child of the "heroesElem" element by using the "appendChild" method
     */

    for (const hero of heroes) {
      heroesElem.appendChild(createHeroElement(hero))
    }
  }

  function clearHeroElements() {
    const heroesElem = document.getElementById('heroes')
    heroesElem.innerHTML = ''
  }

  function onSubmit(event) {
    event.preventDefault()
  }

  async function onUpdate() {
    const formElem = document.getElementById('form')
    selectedHero.name = formElem.name.value
    selectedHero.superpower = formElem.superpower.value

    /*
     * MISSION IV: Updating the hero with the new information
     * 1. Send a PUT request which consists of the hero information to the back-end to update the hero
     *   1.1 Use the "updateHero" function, declared in the apis.js file, and pass the "selectedHero" as its parameter
     * Note: Don't forget that it's an asynchronous function, therefore, you need to use the "await" keyword
     */

    await updateHero(selectedHero)

    clearHeroElements()
    displayHeroes(heroes)

    showForm(false)
  }

  async function onDelete() {
    /*
     * MISSION V: Deleting the selected hero
     * 1. Send a DELETE request along with the hero id
     *   1.1 Use the "deleteHero" function, declared in the apis.js file, and pass the selected hero's id as its parameter
     * Note: Don't forget that it's an asynchronous function, therefore, you need to use the "await" keyword
     */

    const heroIndex = heroes.findIndex((hero) => hero.id === selectedHero.id)
    heroes.splice(heroIndex, 1)

    await deleteHero(selectedHero.id)

    clearHeroElements()
    displayHeroes(heroes)

    showForm(false)
  }

  function setupListeners() {
    const formElem = document.getElementById('form')
    formElem.addEventListener('submit', onSubmit)

    const updateBtn = document.getElementById('update')
    updateBtn.addEventListener('click', onUpdate)

    const deleteBtn = document.getElementById('delete')
    deleteBtn.addEventListener('click', onDelete)
  }

  async function run() {
    setupListeners()

    /*
     * MISSION I: Fetching all heroes from the back-end
     * 1. Use the "getAllHeroes" function to fetch all existing heroes
     * 2. Set the result to the "heroes" variable. Note that the variable has been declared at the beginning of the file,
     *    thus, we should update its value instead of creating a new one
     * Note: Don't forget that the "getAllHeroes" is an asynchronous function, therefore, you need to use the "await" keyword
     */

    heroes = await getAllHeroes()

    displayHeroes(heroes)
  }
  run()
})()
