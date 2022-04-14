const API_URL = 'http://localhost:8080'

async function getAllHeroes() {
  const result = await fetch(`${API_URL}/heroes`)
  return await result.json()
}

async function updateHero(hero) {
  return await fetch(`${API_URL}/heroes/${hero.id}`, {
    method: 'PUT',
    headers: {
      'Content-type': 'application/json; charset=UTF-8',
    },
    body: JSON.stringify(hero),
  })
}

async function deleteHero(id) {
  return await fetch(`${API_URL}/heroes/${id}`, {
    method: 'DELETE',
  })
}
