'use strict'

/** @typedef {import('@adonisjs/framework/src/Request')} Request */
/** @typedef {import('@adonisjs/framework/src/Response')} Response */
/** @typedef {import('@adonisjs/framework/src/View')} View */

/**
 * Resourceful controller for interacting with contactos
 */
  const Database = use('Database')
  const Contacto = use('App/Models/Contacto')

class ContactoController {
  /**
   * Show a list of all contactos.
   * GET contactos
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async index ({ request, response, view }) {
    let contactos = await Contacto.all()
    return response.status(201).json(contactos)
  }

  async getByUser({params, request, response, view}) {
    let { id } = params
    let contactos = Database.table('contactos').select('*').where('user_id',id)
    return contactos
  }

  /**
   * Render a form to be used for creating a new contacto.
   * GET contactos/create
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async create ({ request, response, view }) {
  }

  /**
   * Create/save a new contacto.
   * POST contactos
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async store ({ request, response }) {
    let contacto_data = request.all()
    let contacto = await Contacto.create(contacto_data)
    return response.status(201).json({
      message : 'Contacto saved'
    })
  }

  /**
   * Display a single contacto.
   * GET contactos/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async show ({ params, request, response, view }) {
    let { id } = params
    let contacto = await Contacto.find(id)
    return response.status(201).json(contacto)
  }

  /**
   * Render a form to update an existing contacto.
   * GET contactos/:id/edit
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async edit ({ params, request, response, view }) {
  }

  /**
   * Update contacto details.
   * PUT or PATCH contactos/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async update ({ params, request, response }) {
    let {id } = params
    let contacto_data = request.all()

    let contacto = await Contacto.findOrFail(id)

    contacto.merge(contacto_data)
    await contacto.save()

    return response.status(201).json({message: 'Contacto Updated'})
  }

  /**
   * Delete a contacto with id.
   * DELETE contactos/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async destroy ({ params, request, response }) {
    let { id } = params

    let contacto = await Contacto.findOrFail(id)
    await contacto.delete()

    return response.status(201).json({
      message : 'contacto deleted'
    })
  }
}

module.exports = ContactoController
